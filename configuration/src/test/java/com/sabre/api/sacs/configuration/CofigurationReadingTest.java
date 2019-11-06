package com.sabre.api.sacs.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sabre.api.sacs.configuration.ConfigurationConfig;
import com.sabre.api.sacs.configuration.SacsConfiguration;

@ContextConfiguration(classes={ConfigurationConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CofigurationReadingTest {

	private static final Logger LOG = LogManager.getLogger(CofigurationReadingTest.class.getName());

	@Autowired
	private SacsConfiguration config;

	
	@Test
	public void testReadRestProperty() throws ConfigurationException {
		LOG.info(config.getRestProperty("prop1"));
		Assert.assertEquals("value2", config.getRestProperty("prop1"));
	}

	@Test
	public void testReadEncodedRestProperty() throws ConfigurationException {
		LOG.info(config.getEncodedRestProperty("encoded"));
		Assert.assertEquals("odkodujToBitch!", config.getEncodedRestProperty("encoded"));
	}
	
	@Test
	public void testReadNoRestProperty() {
	    LOG.info(config.getRestProperty("invalid_key"));
	    Assert.assertNull(config.getRestProperty("invalid_key"));
	}
}
