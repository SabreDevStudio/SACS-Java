package com.sabre.api.sacs.flowtrigger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sabre.api.sacs.contract.soap.Security;
import com.sabre.api.sacs.soap.pool.SessionPool;
import com.sabre.api.sacs.workflow.SharedContext;
import com.sabre.api.sacs.workflow.Workflow;

/**
 * The REST webservice exposing web methods for running the workflows.
 */
@RestController
public class WorkflowTrigger {

	@Autowired
	private Workflow restWorkflow;
	
	@Autowired
	private SessionPool sessionPool;
	
	@Autowired
	private ApplicationContext ctx;
	
	/**
	 * Exposes the SOAP workflow.
	 * @return map of SOAP call results.
	 */
	@RequestMapping(value="/orchestratedFlow", method=RequestMethod.GET, produces="application/json")
	public Map<String, Object> orchestratedFlow() {
		Map<String, Object> result = new HashMap<>();
		Workflow orchestratedWorkflow = (Workflow) ctx.getBean("orchestratedWorkflow");
		SharedContext wfResult = orchestratedWorkflow.run();
		for (String request : wfResult.getKeys()) {
			result.put(request, wfResult.getResult(request));
		}
		return result;
	}
	
	/**
	 * Exposes REST workflow.
	 * @return map of REST call results.
	 */
	@RequestMapping(value="/restFlow", method=RequestMethod.GET, produces="application/json")
	public Map<String, Object> restFlow() {
        Map<String, Object> result = new HashMap<>();
        SharedContext wfResult = restWorkflow.run();
        for (String request : wfResult.getKeys()) {
            result.put(request, wfResult.getResult(request));
        }
        return result;
	}
	
	/**
	 * Returns a collection of sessions, that are being used.
	 * @return collection of busy sessions.
	 */
	@RequestMapping(value="/getSessions", method=RequestMethod.GET, produces="application/json")
	public Collection<Security> getSessions() {
	    return sessionPool.getBusy();
	}
	
	/**
	 * Returns an array of available SOAP sessions.
	 * @return array of free sessions.
	 */
	@RequestMapping(value="/getAvailableSessions", method=RequestMethod.GET, produces="application/json")
	public Security[] getAvailableSessions() {
	    return sessionPool.getAvailable();
	}
	
	/**
	 * Refreshes a session.
	 * @param session session to be pinged.
	 * @return Success message.
	 */
	@RequestMapping(value="/pingSession", method=RequestMethod.PUT, consumes="application/json", produces="text/plain")
	public String pingSession(@RequestBody Security session) {
	    sessionPool.refresh(session);
	    return "SUPCIO!";
	}
}
