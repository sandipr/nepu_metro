package com.nepu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nepu.ticket.model.TicketConfig;
import com.nepu.ticket.model.Ticket;

/*
 * Service class for Ticket Management
 */

public class TicketManagementServiceImpl implements TicketManagementService{

	private static TicketManagementService ticketMangementService = new TicketManagementServiceImpl();

	private TicketManagementServiceImpl() {

	}

	/*
	 * Dummy  - will be REST API
	 */
	public static TicketManagementService getService() {
		return ticketMangementService;
	}

	Map<String,List<Ticket>> tickets  =  new HashMap<String,List<Ticket>>();

	@Override
	public Ticket createTicket(String userID,TicketConfig.ZONE startZone,TicketConfig.ZONE endZOne) {

		Ticket ticket =  new Ticket(userID,startZone,endZOne);

		return ticket;
	}

	/*
	 * Dummy  - will be sent to Kafka
	 */

	@Override
	public void sentNotification(Ticket ticket) {

		List<Ticket> ticketsList = tickets.get(ticket.getUser());
		if(ticketsList == null)
			ticketsList =  new ArrayList<Ticket>();
		ticketsList.add(ticket);
		tickets.put(ticket.getUser(), ticketsList);
	}


	@Override
	public void clearData() {

		tickets.clear();
	}

	@Override
	public List<Ticket> loadAllTickets(String userID) {

		return tickets.get(userID);
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		// TODO Auto-generated method stub

	}





}
