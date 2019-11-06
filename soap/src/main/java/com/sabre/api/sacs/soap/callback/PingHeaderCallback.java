package com.sabre.api.sacs.soap.callback;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapMessage;

import com.sabre.api.sacs.contract.soap.MessageHeader;
import com.sabre.api.sacs.contract.soap.Security;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Class adding header for calls that refresh sessions.
 */
@Controller
public class PingHeaderCallback implements HeaderCallback {

    private static final String MESSAGE_HEADER_PACKAGE = MessageHeader.class.getPackage().getName();
    private static final String SECURITY_PACKAGE = Security.class.getPackage().getName();

    @Autowired
    private MessageHeaderFactory messageHeaderFactory;
    
    private Security sessionToPing;

    private Jaxb2Marshaller marshaller;

    @PostConstruct
    private void init() {
        marshaller = new Jaxb2Marshaller();
        StringBuffer buffer = new StringBuffer()
                .append(MESSAGE_HEADER_PACKAGE)
                .append(":")
                .append(SECURITY_PACKAGE);
        String contextPath = buffer.toString();
        marshaller.setContextPath(contextPath);
    }

    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {

        MessageHeader header = messageHeaderFactory.getMessageHeader("OTA_PingRQ");
        header.setConversationId("ping_session");
        SoapHeader soapHeaderElement = ((SoapMessage)message).getSoapHeader();
        marshaller.marshal(header, soapHeaderElement.getResult());
        marshaller.marshal(sessionToPing, soapHeaderElement.getResult());

    }
    
    /**
     * Sets the security object of the session which must be refreshed.
     * @param sessionToPing security object with ATH
     */
    public void setSessionToPing(Security sessionToPing) {
        this.sessionToPing = sessionToPing;
    }

    @Override
    public void setWorkflowContext(SharedContext workflowContext) {
    }

}
