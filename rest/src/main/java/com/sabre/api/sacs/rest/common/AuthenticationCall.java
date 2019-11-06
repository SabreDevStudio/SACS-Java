package com.sabre.api.sacs.rest.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sabre.api.sacs.configuration.SacsConfiguration;
import com.sabre.api.sacs.rest.domain.auth.AuthResponse;
import com.sabre.api.sacs.rest.interceptor.LoggingRequestInterceptor;

/**
 * Class creating call for authenticating and obtaining the AuthToken.
 * The call is being used in the TokenHolder class when the token, that
 * is being held there is either expired or not created yet.
 */
@Controller
public class AuthenticationCall {

    @Autowired
    private SacsConfiguration config;
    
    @Autowired
    private CredentialsBuilder credentialsBuilder;
    
    @Autowired
    private TokenHolder tokenHolder;
    
    public void doCall() {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestInterceptor ri = new LoggingRequestInterceptor();
        List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
        ris.add(ri);
        restTemplate.setInterceptors(ris);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("grant_type", "client_credentials");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);  
        headers.add("Authorization", "Basic " + credentialsBuilder.getCredentialsString());
        headers.add("Accept", "*/*");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        AuthResponse authToken = restTemplate.postForObject(config.getRestProperty("environment") + "/v2/auth/token", requestEntity, AuthResponse.class);
        tokenHolder.resetToken(authToken);
        
    }
     
    
    
}
