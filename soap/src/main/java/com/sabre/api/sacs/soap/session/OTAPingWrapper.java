package com.sabre.api.sacs.soap.session;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.contract.session.ping.OTAPingRQ;
import com.sabre.api.sacs.contract.session.ping.OTAPingRS;
import com.sabre.api.sacs.contract.soap.Security;
import com.sabre.api.sacs.soap.callback.PingHeaderCallback;
import com.sabre.api.sacs.soap.interceptor.FaultInterceptor;
import com.sabre.api.sacs.soap.interceptor.LoggingInterceptor;

/**
 * Wrapper used to run the OTA_Ping service, which is used to refresh the session.
 */
@Controller
public class OTAPingWrapper extends WebServiceGatewaySupport {

    @Autowired
    private SacsConfiguration configuration;

	@Autowired
	private LoggingInterceptor loggingInterceptor;
	
	@Autowired
	private FaultInterceptor faultInterceptor;
	
	@Autowired
	private PingHeaderCallback pingHeaderCallback;
	
	private List<ClientInterceptor> interceptors = new ArrayList<>();
	
	private Jaxb2Marshaller marshaller;
	
	@PostConstruct
	private void init() {
		interceptors.add(loggingInterceptor);
		interceptors.add(faultInterceptor);
		setInterceptors(interceptors.toArray(new ClientInterceptor[0]));
		marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(OTAPingRQ.class.getPackage().getName());
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);
	}
	
	public OTAPingRS executePing(Security sessionToPing) {
		OTAPingRS result = new OTAPingRS();
		this.setDefaultUri(configuration.getSoapProperty("environment"));
		this.pingHeaderCallback.setSessionToPing(sessionToPing);
		this.setMarshaller(marshaller);
		result = (OTAPingRS) this.getWebServiceTemplate().marshalSendAndReceive(generateRequest(), pingHeaderCallback);
		return result;
	}
	
	private OTAPingRQ generateRequest() {
		OTAPingRQ ping = new OTAPingRQ();
		
		ping.setEchoData("Pinging session...");
		ping.setVersion(configuration.getSoapProperty("OTA_PingRQVersion"));
		
		return ping;
	}
}
