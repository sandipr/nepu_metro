package com.nepu.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nepu.ticket.model.TicketConfig;
import com.nepu.ticket.model.TicketConfig.ZONE;
import com.nepu.ticket.model.TicketDataCache;
import com.nepu.ticket.model.WeeklyTicketModel;
import com.nepu.ticket.model.DailyTicketModel;
import com.nepu.ticket.model.Ticket;

/*
 * Service class for Ticket Management
 */

public class TicketManagementServiceImpl implements TicketManagementService{

	private static TicketManagementService ticketMangementService = new TicketManagementServiceImpl();
	
	private static TicketDataCache cache  = new TicketDataCache();

	private TicketManagementServiceImpl() {
		
	}

	/*
	 * Dummy  - will be REST API
	 */
	public static TicketManagementService getService() {
		return ticketMangementService;
	}

	//Map<String,List<Ticket>> tickets  =  new HashMap<String,List<Ticket>>();

	@Override
	public Ticket createTicket(String userID, LocalDateTime bookingTime, TicketConfig.ZONE startZone,TicketConfig.ZONE endZOne) {

		Ticket ticket =  new Ticket(userID,bookingTime,startZone,endZOne);
		
		cache.addTicket(ticket);

		return ticket;
	}

	/*
	 * Dummy  - will be sent to Kafka
	 */

	@Override
	public void sentNotification(Ticket ticket) {

		//cache.addTicket(ticket);
	}


	@Override
	public void clearData() {

		
	}

	@Override
	public DailyTicketModel getDailyTicketModel(String userID, DayOfWeek dayOfWeek) {

		return cache.getDailyTicketModel(userID, dayOfWeek);
	}

	@Override
	public WeeklyTicketModel getWeekyTicketModel(String userID) {

		return cache.getWeeklyTicketModel(userID);
	}
	
	@Override
	public void deleteTicket(Ticket ticket) {
		// TODO Auto-generated method stub

	}

	


}
