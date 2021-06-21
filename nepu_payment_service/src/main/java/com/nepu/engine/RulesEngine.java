package com.nepu.engine;



import com.nepu.ticket.model.Ticket;

public interface RulesEngine {
	
	public void process(Ticket ticket) throws Exception; 
	
	public boolean start();
	

	public boolean stop();

}
