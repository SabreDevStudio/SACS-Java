package com.sabre.api.sacs.rest.common;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.configuration.SacsConfiguration;

/**
 * Class responsible for creating the authentication string
 * based on the credentials held in the Configuration.
 */
@Controller
public class CredentialsBuilder {
    
    @Autowired
    private SacsConfiguration config;

    /**
     * Constructs the credentials string. It gets the required data from
     * the Configuration module and encodes it as it is described here:
     * {@link https://developer.sabre.com/docs/read/rest_basics/authentication}
     * @return the encoded credentials to be used in the authentication header.
     */
    public String getCredentialsString() {
        StringBuilder credentials = new StringBuilder();
        
        credentials.append(config.getRestProperty("formatVersion"))
            .append(":")
            .append(config.getRestProperty("userId"))
            .append(":")
            .append(config.getRestProperty("group"))
            .append(":")
            .append(config.getRestProperty("domain"));
        
        String secret = b64(config.getRestProperty("clientSecret"));
        return b64(b64(credentials.toString()) + ":" + secret);
    }
    
    //just a shortcut method
    private String b64(String toEncode) {
        return Base64.encodeBase64String(toEncode.getBytes());
    }
}
