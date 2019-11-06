package com.sabre.api.sacs.rest.domain;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Base class for GET requests domain objects. All request classes should extend this class.
 * It has a method, which builds a query string based on the properties of the extending
 * object.
 */
public class BaseDomainRequest {
    
    private static final Logger LOG = LogManager.getLogger(BaseDomainRequest.class);

    /**
     * Uses reflection mechanism to build a query string based on the extending
     * object's properties. Properties with null values are not included in
     * the string.
     * @return query string for GET call.
     */
    @SuppressWarnings("rawtypes")
    public String toRequestQuery() {
        StringBuilder requestQuery = new StringBuilder("?");
        try {
            Class c = Class.forName(this.getClass().getName());
            Method m[] = c.getDeclaredMethods();
            Object oo;
            for (int i = 0; i < m.length; i++)
                if (m[i].getName().startsWith("get")) {
                    oo = m[i].invoke(this, (Object[])null);
                    if (oo != null) {
                        requestQuery.append(m[i].getName().substring(3).toLowerCase() + "="
                                + String.valueOf(oo) + "&");
                    }
                }
        } catch (Throwable e) {
            LOG.catching(e);
        }

        return requestQuery.deleteCharAt(requestQuery.lastIndexOf("&")).toString();
    }
}
