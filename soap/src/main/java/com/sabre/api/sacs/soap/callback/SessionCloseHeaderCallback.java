package com.sabre.api.sacs.soap.callback;

import java.io.IOException;

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
 * Callback which adds the header of the session to close. Used in the messages that
 * are being sent when the application exits.
 */
@Controller
public class SessionCloseHeaderCallback implements HeaderCallback {

    private MessageHeader header;
    private SharedContext workflowContext;
    private Security toClose;
    @Autowired
    private MessageHeaderFactory messageHeaderFactory;

    @Autowired
    private Jaxb2Marshaller marshaller;

    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {

        header = messageHeaderFactory.getMessageHeader("SessionCloseRQ");
        header.setConversationId(workflowContext.getConversationId());

        SoapHeader soapHeaderElement = ((SoapMessage) message).getSoapHeader();

        marshaller.marshal(header, soapHeaderElement.getResult());
        marshaller.marshal(toClose, soapHeaderElement.getResult());

    }

    /**
     * Sets the security object of the session that is to be closed.
     * @param session security object with ATH
     */
    public void setSession(Security session) {
        this.toClose = session;
    }
    
    public void setWorkflowContext(SharedContext context) {}

}
