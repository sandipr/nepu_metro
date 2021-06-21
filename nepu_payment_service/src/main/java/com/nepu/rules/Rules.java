package com.nepu.rules;

import com.nepu.constants.Config;
import com.nepu.ticket.model.Ticket;


public interface Rules {
	
	public void initRule(Config config);
	
	public void applyRule(Ticket ticket) throws Exception;
	
}
