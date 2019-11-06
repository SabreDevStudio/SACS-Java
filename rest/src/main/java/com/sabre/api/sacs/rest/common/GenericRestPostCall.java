package com.sabre.api.sacs.rest.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.rest.interceptor.LoggingRequestInterceptor;
import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Class responsible for executing the POST call.
 * @param <RQ> the request domain class.
 */
@Controller
@Scope("prototype")
public class GenericRestPostCall<RQ> {

    private static final Logger LOG = LogManager.getLogger(GenericRestPostCall.class);

    @Autowired
    private SacsConfiguration config;
    
    @Autowired
    private TokenHolder tokenHolder;
    
    private RQ request; 
    
    private String url;
    
    /**
     * Adds interceptors, creates request string, adds request body and sends the request.
     * Returns the response object.
     * @param cls Class of the response object.
     * @return response object.
     */
    public <RS> RS doCall(Class<RS> cls, SharedContext context) {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestInterceptor ri = new LoggingRequestInterceptor();
        List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
        ris.add(ri);
        restTemplate.setInterceptors(ris);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokenHolder.getTokenString());
        headers.add("Accept", "*/*");

         HttpEntity<RQ> requestEntity = new HttpEntity<>(request,
         headers);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        RS callResult = null;
        try {
            callResult = cls.newInstance();
        } catch (InstantiationException e) {
            LOG.catching(e);
        } catch (IllegalAccessException e) {
            LOG.catching(e);
        }
        try {
            callResult = restTemplate.postForObject(url, requestEntity, cls);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                tokenHolder.setInvalid(true);
                context.setFaulty(true);
            }
        }
        return callResult;
    }
    
    public void setRequest(RQ request) {
        this.request = request;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}
