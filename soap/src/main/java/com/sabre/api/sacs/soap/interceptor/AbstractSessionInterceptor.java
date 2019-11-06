package com.sabre.api.sacs.soap.interceptor;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;

import com.sabre.api.sacs.contract.soap.MessageHeader;
import com.sabre.api.sacs.contract.soap.Security;

/**
 * Class containing methods to extract the session objects from the server response.
 */
public abstract class AbstractSessionInterceptor implements ClientInterceptor {

    private static final Logger LOG = LogManager.getLogger(AbstractSessionInterceptor.class);

    private static final String securityNs = "http://schemas.xmlsoap.org/ws/2002/12/secext";
    private static final String securityLocalName = "Security";

    private static final String headerNs = "http://www.ebxml.org/namespaces/messageHeader";
    private static final String headerLocalName = "MessageHeader";

    private static final QName securityQName = new QName(securityNs, securityLocalName);
    private static final QName headerQName = new QName(headerNs, headerLocalName);

    @Override
    public abstract boolean handleRequest(MessageContext messageContext);

    @Override
    public abstract boolean handleResponse(MessageContext messageContext);

    @Override
    public boolean handleFault(MessageContext messageContext) {
        return false;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) {
    }

    Security extractSecurityFromMessageContext(MessageContext messageContext) throws JAXBException {
        Jaxb2Marshaller unmarshaller = getUnmarshaller();
        SoapMessage message = (SoapMessage) messageContext.getResponse();
        Source securitySource = message.getSoapHeader().examineHeaderElements(securityQName).next().getSource();
        return (Security) unmarshaller.unmarshal(securitySource);
    }

    MessageHeader extractMessageHeaderFromMessageContext(MessageContext messageContext) throws JAXBException {

        Jaxb2Marshaller unmarshaller = getUnmarshaller();
        SoapMessage message = (SoapMessage) messageContext.getResponse();
        Source headerSource = message.getSoapHeader().examineHeaderElements(headerQName).next().getSource();
        return (MessageHeader) unmarshaller.unmarshal(headerSource);
    }

    void logTokenAndConversationIdFromMessage(String token, String conversationId) {

        StringBuffer buffer = new StringBuffer();
        buffer.append("Session token: " + token).append("\n");
        buffer.append("Conversation-Id: " + conversationId).append("\n");

        String logMessage = buffer.toString();
        LOG.info(logMessage);
    }

    boolean isSessionCloseRS(MessageHeader header) {
        return header.getAction().equalsIgnoreCase("SessionCloseRS");
    }

    Jaxb2Marshaller getUnmarshaller() throws JAXBException {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(getContextPath());

        return marshaller;
    }

    private String getContextPath() {
        StringBuilder builder = new StringBuilder();

        builder.append(Security.class.getPackage().getName()).append(":");
        builder.append(MessageHeader.class.getPackage().getName()).append(":");

        return builder.toString();
    }
}
