package com.sabre.api.sacs.soap.pool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.soap.Security;
import com.sabre.api.sacs.soap.session.IgnoreTransactionWrapper;
import com.sabre.api.sacs.soap.session.OTAPingWrapper;
import com.sabre.api.sacs.soap.session.SessionCloseWrapper;
import com.sabre.api.sacs.soap.session.SessionCreateWrapper;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Class holding the session pool. It is responsible for filling the pool with
 * session at init time, keeping the busy sessions and handing them to the 
 * correct workflows, as well as keeping the new workflows waiting for ther session.
 */
@Service
public class SessionPool {

    private static final Logger LOG = LogManager.getLogger(SessionPool.class);

    private int maxSize;

    private Map<String, Security> busy;

    private BlockingQueue<Security> available;

    @Autowired
    private SacsConfiguration configuration;

    @Autowired
    private SessionCreateWrapper sessionCreateCall;

    @Autowired
    private SessionCloseWrapper sessionCloseCall;

    @Autowired
    private IgnoreTransactionWrapper ignoreTransaction;

    @Autowired
    private OTAPingWrapper pingCall;

    @PostConstruct
    private void init() {
        this.maxSize = Integer.parseInt(configuration.getSoapProperty("sessionPoolSize"));
        available = new LinkedBlockingQueue<>(this.maxSize);
        busy = new HashMap<>();
        SharedContext context = new SharedContext();
        for (int i = 0; i < maxSize; i++) {
            context.setConversationId("InitialConversationIdNo_" + (i+1));
            sessionCreateCall.openSession(context);
        }
    }

    public void destroy() throws InterruptedException {
        while (!available.isEmpty()) {
            sessionCloseCall.closeSession(available.take());
        }
    }

    /**
     * One of two most important methods in the session pool. Takes care
     * of giving session to the workflow based on the ConversationId kept in the {@link SharedContext}.
     * Keeps workflows waiting when no session is available, until one of the running workflows returns.
     * @param context the context where workflow's ConversationId is kept.
     * @return {@link Security} object identifying the session.
     */
    public Security getFromPool(SharedContext context) {
        LOG.info("Looking for session for ConversationID: " + context.getConversationId());
        Security result = busy.get(context.getConversationId());
        // there is a session for this conversationId
        if (result != null) {
            LOG.info("Found session for ConversationID: " + context.getConversationId());
            return result;
        } else {
            // if there is no session for this conversationId
            LOG.info("Did not find a session for the conversationID: " + context.getConversationId());
            try {
                // wait for an available session
                LOG.info("Waiting for available session for ConversationID:" + context.getConversationId());
                result = available.take();
                busy.put(context.getConversationId(), result);
                LOG.info("Took for ConversationID:" + context.getConversationId());
            } catch (InterruptedException e) {
                LOG.catching(e);
            }

            return result;
        }
    }
    
    @Scheduled(cron=" 0 1 0 ? * MON ")
    private void recreatePool() {
        init();
    }

    public void addToPool(Security session) {
        boolean added = available.offer(session);
        LOG.debug("Added to pool? " + added);
    }

    /**
     * Second of the two most important methods managing the pool. Responsible for 
     * cleaning the session and returning it to the pool.
     * @param session {@link Security} object to be returned.
     * @param conversationId workflow's ConversationId.
     */
    public void returnToPool(String conversationId) {
        LOG.info("Returning session for ConversationID: " + conversationId);
        Security toReturn = busy.get(conversationId);
        SharedContext temp = new SharedContext();
        temp.setConversationId(conversationId);
        if (toReturn != null) {
            ignoreTransaction.executeRequest(toReturn, temp);
            
            if (!available.contains(toReturn)) {
                boolean added = available.offer(toReturn);
                LOG.info("Returned to pool? " + added);
            } else {
                LOG.info("This session is already in pool.");
            }
        } else {
            LOG.info("No session in the busy map for ConversationID: " + conversationId);
            LOG.info("Busy map size: " + busy.size());
            LOG.info("Available queue size: " + available.size());
        }
        busy.remove(conversationId);
    }

    public Security[] getAvailable() {
        return available.toArray(new Security[] {});

    }

    public Collection<Security> getBusy() {
        return busy.values();
    }

    public void refresh(Security session) {
        pingCall.executePing(session);
    }

    /**
     * Refreshes all available session at a fixed time rate.
     */
    @Scheduled(fixedRate = 1000 * 60 * 10)
    private void refreshAll() {
        List<Security> toRefresh = new ArrayList<>();
        available.drainTo(toRefresh);
        for (Security session : toRefresh) {
            refresh(session);
        }
        available.addAll(toRefresh);
    }

    public void clear() {
        available.clear();
        busy.clear();
    }
}
