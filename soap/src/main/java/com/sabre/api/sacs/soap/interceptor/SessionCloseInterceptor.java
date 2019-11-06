package com.sabre.api.sacs.soap.interceptor;

import javax.xml.bind.JAXBException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.context.MessageContext;

import com.sabre.api.sacs.contract.soap.MessageHeader;

/**
 * This is an interceptor class for working with Spring Web Services framework.
 * It's responsibility is to ensure that processed message is a SessionCloseRQ
 * response message, validate if session being closed is the active one and
 * inform SessionPool instance about closing session.
 */
@Controller
public class SessionCloseInterceptor extends AbstractSessionInterceptor {

    private static final Logger LOG = LogManager.getLogger(SessionCloseInterceptor.class);

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {

        try {
            MessageHeader header = extractMessageHeaderFromMessageContext(messageContext);

            if (!isSessionCloseRS(header)) {
                throw new UnsupportedOperationException("This interceptors works with SessionCloseRQ only");
            }

        } catch (JAXBException | NullPointerException e) {
            LOG.fatal("Error occurred during retrieving session token", e);
        }
        return true;
    }

}
