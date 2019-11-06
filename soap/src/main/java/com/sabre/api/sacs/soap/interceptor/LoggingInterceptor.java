package com.sabre.api.sacs.soap.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

import com.sabre.api.sacs.soap.session.XMLPrettifier;

/**
 * This is an interceptor class for working with Spring Web Services framework.
 * It's responsibility is to log messages going in and out using standard system
 * logger.
 */
@Component
public class LoggingInterceptor implements ClientInterceptor {

    private static final Logger LOGGER = LogManager.getLogger(LoggingInterceptor.class);

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {

        try {
            logRequestMessage(messageContext);
        } catch (IOException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        try {
            logResponseMessage(messageContext);
        } catch (IOException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    private void logRequestMessage(MessageContext messageContext) throws IOException {
        String responseText = getRawRequestMessage(messageContext);
        LOGGER.debug("\nOutgoing request:\n" + XMLPrettifier.pretify(responseText));
    }

    private void logResponseMessage(MessageContext messageContext) throws IOException {
        String responseText = getRawResponseMessage(messageContext);
        LOGGER.debug("\nIncoming response:\n" + XMLPrettifier.pretify(responseText));
    }

    private String getRawResponseMessage(MessageContext messageContext) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        messageContext.getResponse().writeTo(outputStream);
        return outputStream.toString("UTF-8");
    }

    private String getRawRequestMessage(MessageContext messageContext) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        messageContext.getRequest().writeTo(outputStream);
        return outputStream.toString("UTF-8");
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        return false;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {

    }

}
