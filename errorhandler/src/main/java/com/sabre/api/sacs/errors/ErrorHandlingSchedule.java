package com.sabre.api.sacs.errors;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.workflow.SharedContext;
import com.sabre.api.sacs.workflow.visitor.Visitable;
import com.sabre.api.sacs.workflow.visitor.Visitor;

/**
 * Class responsible for running error handling task.
 * The task reviews the list of failed workflows
 * and employs the {@link ErrorHandlingVisitor} to use a proper solution.
 */
@Controller
public class ErrorHandlingSchedule {

	private static final Logger LOG = LogManager.getLogger(ErrorHandlingSchedule.class);
	
	private List<Visitable> systemFailures = new ArrayList<>();
	
	private List<Visitable> permanentlyFailed = new ArrayList<>();
	
	@Autowired
	private Visitor errorHandlingVisitor;
	
	/**
	 * Adds a {@link SharedContext} of the failed {@link Workflow} to the list of
	 * failed ones. 
	 * @param systemFailure {@link SharedContext} containing the failed {@link Workflow} 
	 */
	public void addSystemFailure(SharedContext systemFailure) {
		this.systemFailures.add(systemFailure);
	}
	
	//executed every 20 seconds
	/**
	 * Method is executed in configured periods of time. Runs the error handling on 
	 * all the failed workflows.
	 */
	@Scheduled(cron="0/20 0/1 * 1/1 * ?")
	public void handleFailures() {
		LOG.info("Handling failures...");
		LOG.info("Failures count: " + systemFailures.size());
		List<Visitable> handled = new ArrayList<>();
		for (Visitable failure: systemFailures) {
		    if (failure.getRerun() < failure.getRerunLimit()) {
    			handleFailure(failure, errorHandlingVisitor);
    			LOG.info("Failure sent to handling.");
		    } else {
		        permanentlyFailed.add(failure);
		    }
            handled.add(failure);
		}
		systemFailures.removeAll(handled);
	}
	
	/**
	 * Sends the failure to the handler in the separate thread.
	 * @param failure failure to be handled.
	 * @param handler handler to handle the failure.
	 */
	private void handleFailure(final Visitable failure, final Visitor handler) {
	    new Thread(new Runnable() {

            @Override
            public void run() {
                failure.accept(handler);
            }
        }).start();
	}

}
