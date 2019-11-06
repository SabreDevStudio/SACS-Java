package com.sabre.api.sacs.rest.common;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.rest.domain.auth.AuthResponse;

/**
 * Class controlling the usage of the authentication token. If the 
 * token has expired or was not created yet, it sends the authentication
 * call.
 */
@Controller
public class TokenHolder {

    private AuthResponse token;
    
    private Date expirationDate;
    
    private boolean invalid = false;
    
    @Autowired
    private AuthenticationCall authCall;
    
    /**
     * Retrieves the authentication token. If the token has expired,
     * it sends the authentication call.
     * @return token string to be used in all calls.
     */
    public String getTokenString() {
        if (expirationDate == null || new Date().getTime() > expirationDate.getTime() || invalid) {
            authCall.doCall();
        }
        return token.getAccessToken();
    }
    
    /**
     * Sets the token and resets it's expiration time.
     * @param token the new token to be set.
     */
    public void resetToken(AuthResponse token) {
        this.token = token;
        this.invalid = false;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, Integer.parseInt(token.getExpiresIn()));
        expirationDate = cal.getTime();
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }
}
