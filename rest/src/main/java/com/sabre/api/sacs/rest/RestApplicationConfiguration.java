package com.sabre.api.sacs.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.sabre.api.sacs.configuration.ConfigurationConfig;
import com.sabre.api.sacs.rest.activities.LeadPriceCalendarActivity;
import com.sabre.api.sacs.workflow.SharedContext;
import com.sabre.api.sacs.workflow.Workflow;

/**
 * Module configuration an main class for running test flow.
 */
@SpringBootApplication
@ComponentScan
public class RestApplicationConfiguration {

    private static final Logger LOG = LogManager.getLogger(RestApplicationConfiguration.class);
   
    public static void main(String args[]) {
        final ApplicationContext ctx = SpringApplication.run(
                new Object[] {ConfigurationConfig.class, RestApplicationConfiguration.class}, args);
        Workflow workflow = new Workflow(ctx.getBean(LeadPriceCalendarActivity.class));
        SharedContext sCtx = workflow.run();
        LOG.debug(sCtx);
    }
    
    


}
