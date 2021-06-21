package com.nepu.service;


import java.util.List;

import com.nepu.ticket.model.TicketConfig;
import com.nepu.ticket.model.Ticket;

public interface TicketManagementService {
	
	public Ticket createTicket(String userID,TicketConfig.ZONE startZone,TicketConfig.ZONE endZOne);
	
	public List<Ticket> loadAllTickets(String userID);
	
	public void sentNotification(Ticket ticket);
	
	public void deleteTicket(Ticket ticket);

}
