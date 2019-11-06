package com.sabre.api.sacs.workflow.visitor;

/**
 * Interface of the Visitor pattern. All objects that are to be visited by the visitor
 * should implement it.
 */
public interface Visitable {

    /**
     * Visitor pattern's method for accepting the 
     * @param visitor
     */
	void accept(Visitor visitor);
	
	int getRerunLimit();
	
	int getRerun();
}
