package com.sabre.api.sacs.rest.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AuthRequest {


    @JsonProperty("grant_type")
    private String grantType;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
    
    public static class Builder {
        
        private AuthRequest _req = new AuthRequest();
        
        public AuthRequest build() {
            return _req;
        }
        
        public Builder grantType(String grantType) {
            _req.grantType = grantType;
            return this;
        }
    }
}
