package com.sabre.api.sacs.soap.interceptor;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.context.MessageContext;

import com.sabre.api.sacs.contract.soap.MessageHeader;
import com.sabre.api.sacs.contract.soap.Security;
import com.sabre.api.sacs.soap.pool.SessionPool;

/**
 * This is an interceptor class for working with Spring Web Services framework.
 * It's responsibility is to ensure that processed message is a SessionCreateRQ
 * response message and inform SessionPool instance about open session and
 * it's parameters.
 *
 */
@Component
@Scope("prototype")
public class SessionCreateInterceptor extends AbstractSessionInterceptor {

    private static final Logger LOGGER = LogManager.getLogger(SessionCreateInterceptor.class);

    @Autowired
    SessionPool sessionPool;

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @SuppressWarnings("serial")
    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {

        String token = null;
        String conversationId = null;
        Security security = null;
        try {
            security = extractSecurityFromMessageContext(messageContext);
            MessageHeader header = extractMessageHeaderFromMessageContext(messageContext);

            if (!header.getAction().equalsIgnoreCase("SessionCreateRS")) {
                throw new UnsupportedOperationException("This interceptors works with SessionCreateRQ only");
            }
            token = security.getBinarySecurityToken();
            conversationId = header.getConversationId();

        } catch (JAXBException | NullPointerException e) {
            LOGGER.fatal("Error occurred during retrieving session token", e);
        }

        LOGGER.info("Creating session object for ConversationID: " + conversationId);

        if (token == null | conversationId == null) {
            throw new WebServiceClientException("Couldn't retrieve session token from message") {
            };
        }

        logTokenAndConversationIdFromMessage(token, conversationId);

        sessionPool.addToPool(security);
        return true;
    }
}
