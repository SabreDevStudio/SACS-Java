package com.sabre.api.sacs.workflow.visitor;

import com.sabre.api.sacs.workflow.SharedContext;


public interface Visitor {

	void visit(SharedContext failed);
	
}
