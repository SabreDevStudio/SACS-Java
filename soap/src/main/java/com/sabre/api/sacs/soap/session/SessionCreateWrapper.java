package com.sabre.api.sacs.soap.session;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.client.SoapFaultClientException;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.session.SessionCreateRQ;
import com.sabre.api.sacs.contract.session.SessionCreateRS;
import com.sabre.api.sacs.soap.callback.SessionCreateHeaderCallback;
import com.sabre.api.sacs.soap.interceptor.FaultInterceptor;
import com.sabre.api.sacs.soap.interceptor.LoggingInterceptor;
import com.sabre.api.sacs.soap.interceptor.SessionCreateInterceptor;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Wrapper class that calls the SessionCreate service. Used at the initialization of the SessionPool. 
 */
@Controller
@Scope("prototype")
public class SessionCreateWrapper extends WebServiceGatewaySupport {

    private static final Logger LOGGER = LogManager.getLogger(SessionCreateWrapper.class);

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private SessionCreateInterceptor sessionCreateInterceptor;

    @Autowired
    private FaultInterceptor faultInterceptor;

    @Autowired
    private SacsConfiguration configuration;

    @Autowired
    private SessionCreateHeaderCallback sessionCreateHeaderCallback;
    
    @Autowired
    private Jaxb2Marshaller marshaller;

    private final List<ClientInterceptor> interceptors = new ArrayList<>();

    @PostConstruct
    private void init() {
        interceptors.add(loggingInterceptor);
        interceptors.add(sessionCreateInterceptor);
        interceptors.add(faultInterceptor);

        this.setInterceptors(interceptors.toArray(new ClientInterceptor[0]));
        setDefaultUri(configuration.getSoapProperty("environment"));
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
    }

    private SessionCreateRQ getRequestBody() {

        SessionCreateRQ requestBody = new SessionCreateRQ();

        SessionCreateRQ.POS pos = new SessionCreateRQ.POS();
        SessionCreateRQ.POS.Source source = new SessionCreateRQ.POS.Source();
        source.setPseudoCityCode(configuration.getSoapProperty("pcc"));
        pos.setSource(source);
        requestBody.setPOS(pos);
        
        return requestBody;
    }

    public SessionCreateRS openSession(SharedContext workflowContext) {

        LOGGER.info("Opening session...");

        sessionCreateHeaderCallback.setWorkflowContext(workflowContext);
        Object result = null;
        boolean isFault = false;
        try {
            result = getWebServiceTemplate().marshalSendAndReceive(
                getRequestBody(),
                sessionCreateHeaderCallback
                );
        } catch (SoapFaultClientException e) {
            isFault = true;
            workflowContext.setFaulty(true);
        }
        if (!isFault) {
            LOGGER.info("Session successfully opened for ConversationID " + workflowContext.getConversationId());
        } else {
            return null;
        }

        return (SessionCreateRS)result;
    }
    
    
}
