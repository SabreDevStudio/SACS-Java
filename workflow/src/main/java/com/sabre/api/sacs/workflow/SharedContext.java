package com.sabre.api.sacs.workflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sabre.api.sacs.workflow.visitor.Visitable;
import com.sabre.api.sacs.workflow.visitor.Visitor;

/**
 * Class contains a map of results indexed with a name of the call, indicates
 * whether there was a fault during the workflow execution and also defines how
 * many times should the workflow be rerun in case of a failure; implements the
 * Visitable interface, so it can be visited by the error handling visitor.
 */
public class SharedContext implements Visitable {

    private Map<String, Object> resultsByCall = new HashMap<>();

    private String conversationId;

    private boolean faulty;

    private Workflow owner;

    private int rerunLimit;
    private int rerun = 0;

    /**
     * Adds an object to the shared context results map.
     * 
     * @param requestName
     *            key of the object.
     * @param result
     *            object to be stored in the context.
     */
    public void putResult(String requestName, Object result) {
        resultsByCall.put(requestName, result);
    }

    public Object getResult(String requestName) {
        return resultsByCall.get(requestName);
    }

    public Set<String> getKeys() {
        return resultsByCall.keySet();
    }

    /**
     * Returns the conversation ID of the flow using this context.
     * 
     * @return conversation ID
     */
    public String getConversationId() {
        return conversationId;
    }

    /**
     * Sets the conversation ID of the flow using this context.
     * 
     * @param conversationId
     *            conversation ID to be held by this context.
     */
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    /**
     * Returns the flow which uses this context.
     * 
     * @return the workflow object.
     */
    public Workflow getOwner() {
        return owner;
    }

    public void setOwner(Workflow owner) {
        this.owner = owner;
    }

    /**
     * Returns a boolean flag indicating whether the flow had a fault during its
     * run.
     * 
     * @return true if the flow had a fault.
     */
    public boolean isFaulty() {
        return faulty;
    }

    public void setFaulty(boolean faulty) {
        this.faulty = faulty;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getRerunLimit() {
        return rerunLimit;
    }

    public void setRerunLimit(int rerunLimit) {
        this.rerunLimit = rerunLimit;
    }

    @Override
    public int getRerun() {
        return rerun;
    }

    public void setRerun(int rerun) {
        this.rerun = rerun;
    }

}
