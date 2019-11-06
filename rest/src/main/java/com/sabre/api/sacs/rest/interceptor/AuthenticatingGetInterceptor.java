package com.sabre.api.sacs.rest.interceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.rest.common.TokenHolder;

/**
 * Interceptor class, which adds the 'Authorization' header to the GET calls.
 */
@Controller
public class AuthenticatingGetInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private TokenHolder tokenHolder;
    
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {

        
        request.getHeaders().add("Authorization", "Bearer " + tokenHolder.getTokenString());
        
        ClientHttpResponse response = execution.execute(request, body);

        return response;
    }

}
