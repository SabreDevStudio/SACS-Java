package com.sabre.api.sacs.soap.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

/**
 * This is an interceptor class for working with Spring Web Services framework.
 * It's responsibility is to log fault SOAP message using standard system logger,
 * when fault event occurs.
 */
@Controller
public class FaultInterceptor implements ClientInterceptor {

    private static final Logger LOGGER = LogManager.getLogger( FaultInterceptor.class );
    
    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {

        try {
            logFaultRequest(messageContext);
        } catch(Exception e){
            e.printStackTrace();
        }

        if (messageContext.hasResponse()) {
            try {
                logFaultResponse(messageContext);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return true;
    }

    private void logFaultResponse(MessageContext messageContext) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        messageContext.getResponse().writeTo(out);
        String faultResponse = out.toString("ISO-8859-1");
        LOGGER.error("\nFault response:\n" + faultResponse );
    }

    private void logFaultRequest(MessageContext messageContext) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        messageContext.getRequest().writeTo(out);
        String faultRequest = out.toString("ISO-8859-1");
        LOGGER.error("\nFault request:\n" + faultRequest );
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception e) throws WebServiceClientException {

    }
}
