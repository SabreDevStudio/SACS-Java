package com.sabre.api.sacs.rest.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.sabre.api.sacs.rest.domain.BaseDomainRequest;
import com.sabre.api.sacs.rest.interceptor.AuthenticatingGetInterceptor;
import com.sabre.api.sacs.rest.interceptor.LoggingRequestInterceptor;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Class responsible for executing the GET call.
 * @param <RQ> the request domain class.
 */
@Controller
@Scope("prototype")
public class GenericRestGetCall<RQ extends BaseDomainRequest> {
    
    private static final Logger LOG = LogManager.getLogger(GenericRestGetCall.class);
    
    private String url;
    
    private RQ request;
    
    @Autowired
    private AuthenticatingGetInterceptor authInterceptor;
    
    @Autowired
    private TokenHolder tokenHolder;

    /**
     * Adds interceptors, creates request string with query and sends the request.
     * Returns the response object.
     * @param cls Class of the response object.
     * @return response object.
     */
    public <RS> RS doCall(Class<RS> cls, SharedContext context) {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
        ris.add(new LoggingRequestInterceptor());
        ris.add(authInterceptor);
        restTemplate.setInterceptors(ris);

        RS response = null;
        try {
            response = cls.newInstance();
        } catch (InstantiationException e) {
            LOG.catching(e);
        } catch (IllegalAccessException e) {
            LOG.catching(e);
        }
        try {
            response = restTemplate.getForObject(getRequestString(), cls, new Object[] {}); 
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED))
            tokenHolder.setInvalid(true);
            context.setFaulty(true);
        }
        return response;
    }
    
    private String getRequestString() {
        String result = new String(url);
        
        if (request != null) {
            result += request.toRequestQuery();
        }
        
        return result;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setRequest(RQ request) {
        this.request = request;
    }
}
