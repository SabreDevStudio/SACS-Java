package com.sabre.api.sacs.soap.interceptor;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.context.MessageContext;

import com.sabre.api.sacs.contract.soap.MessageHeader;
import com.sabre.api.sacs.soap.pool.SessionPool;

/**
 * Responsible for returning a session to a pool. Should be added to a last call
 * of the flow.
 */

@Controller
@Scope("prototype")
public class SessionPoolInterceptor extends AbstractSessionInterceptor {

    private static final Logger LOG = LogManager.getLogger(SessionPoolInterceptor.class);

    @Autowired
    private SessionPool sessionPool;

    @Override
    public boolean handleRequest(MessageContext messageContext) {
        return true;
    }

    @SuppressWarnings("serial")
    @Override
    public boolean handleResponse(MessageContext messageContext) {
        String conversationId = null;
        try {
            MessageHeader header = extractMessageHeaderFromMessageContext(messageContext);

            conversationId = header.getConversationId();

        } catch (JAXBException | NullPointerException e) {
            LOG.fatal("Error occurred during retrieving session token", e);
        }

        if (conversationId == null) {
            throw new WebServiceClientException("Couldn't retrieve session token from message") {
            };
        }

        logTokenAndConversationIdFromMessage("", conversationId);

        sessionPool.returnToPool(conversationId);
        return true;
    }

}
