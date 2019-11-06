package com.sabre.api.sacs.soap.callback;

import org.springframework.ws.client.core.WebServiceMessageCallback;

import com.sabre.api.sacs.workflow.SharedContext;

/**
 * Interface used for callback implementations.
 */
public interface HeaderCallback extends WebServiceMessageCallback {
    
    /**
     * Sets the workflow context, so the conversationID set there for workflow
     * can be used to get the correct session from the pool.
     * @param workflowContext the SharedContext for the workflow that calls the webservice where the callback
     * should be used, containing the correct conversationID.
     */
    void setWorkflowContext(SharedContext workflowContext);
}
