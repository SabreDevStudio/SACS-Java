package com.sabre.api.sacs.flowtrigger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;

import com.sabre.api.sacs.rest.RestApplicationConfiguration;
import com.sabre.api.sacs.rest.activities.LeadPriceCalendarActivity;
import com.sabre.api.sacs.soap.SoapApplicationConfiguration;
import com.sabre.api.sacs.soap.orchestratedflow.BargainFinderMaxSoapActivity;
import com.sabre.api.sacs.soap.pool.SessionPool;
import com.sabre.api.sacs.workflow.Workflow;

/**
 * Main application class defining workflows and running the server.
 */
@SpringBootApplication
@ComponentScan
public class FlowTriggerApplication {

	public static void main(String[] args) {
	    System.setProperty("https.protocols", "TLSv1.2");
		final ApplicationContext ctx = SpringApplication.run(
				new Object[] {FlowTriggerApplication.class, SoapApplicationConfiguration.class, RestApplicationConfiguration.class}, args);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				try {
                    ctx.getBean(SessionPool.class).destroy();
                } catch (BeansException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
			}
			
		}));

	}
	
	/**
	 * The SOAP workflow.
	 * @param activity initial activity.
	 * @return workflow instance from Spring context.
	 */
	@Bean
	@Autowired
	@Scope("prototype")
	public Workflow orchestratedWorkflow(BargainFinderMaxSoapActivity activity) {//PassengerDetailsActivity activity) {
		return new Workflow(activity);
	}
	
    /**
     * The REST workflow.
     * @param activity initial activity.
     * @return workflow instance from Spring context.
     */
	@Bean
	@Autowired
	@Scope("prototype")
	public Workflow restWorkflow(LeadPriceCalendarActivity activity) {
	    return new Workflow(activity);
	}
	
}
