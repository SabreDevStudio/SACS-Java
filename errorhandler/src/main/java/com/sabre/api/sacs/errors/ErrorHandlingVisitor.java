package com.sabre.api.sacs.errors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.sabre.api.sacs.workflow.SharedContext;
import com.sabre.api.sacs.workflow.visitor.Visitor;

/**
 * Class implementing the Visitor pattern. Handles the failure in the workflow.
 */
@Controller
public class ErrorHandlingVisitor implements Visitor {

	private static final Logger LOG = LogManager.getLogger(ErrorHandlingVisitor.class);
	
	/**
	 * Runs the workflow again.
	 */
	@Override
	public void visit(SharedContext failed) {
		LOG.info("Handling failure..." + failed.toString());
		failed.setFaulty(false);
		failed.getOwner().run();
	}
	
}
