package com.sabre.api.sacs.configuration;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class reading REST and SOAP config.
 */
@Component
public class SacsConfiguration {
	
	private Configuration restConfig;
	private Configuration soapConfig;

	@Autowired
	private ConfigurationDecoder decoder;
	
	
	/**
	 * Initializes configuration objects with values read from the properties files.
	 * @throws ConfigurationException
	 */
	public SacsConfiguration() throws ConfigurationException {
		restConfig = new PropertiesConfiguration("SACSRestConfig.properties");
		soapConfig = new PropertiesConfiguration("SACSSoapConfig.properties");		
	}

	/**
	 * Returns a value for the REST configuration key.
	 * @param key key.
	 * @return value stored under given key.
	 */
	public String getRestProperty(String key) {
		return restConfig.getString(key);
	}
	
    /**
     * Returns a value for the SOAP configuration key.
     * @param key key.
     * @return value stored under given key.
     */
	public String getSoapProperty(String key) {
		return soapConfig.getString(key);
	}
	
    /**
     * Returns a decoded value for the SOAP configuration key.
     * @param key key.
     * @return value stored under given key.
     */
	public String getEncodedSoapProperty(String key) {
		return decoder.decode(soapConfig.getString(key));
	}

    /**
     * Returns a decoded value for the REST configuration key.
     * @param key key.
     * @return value stored under given key.
     */
	public String getEncodedRestProperty(String key) {
		return decoder.decode(restConfig.getString(key));
	}

}
