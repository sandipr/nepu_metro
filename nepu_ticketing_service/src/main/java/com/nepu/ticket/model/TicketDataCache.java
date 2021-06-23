package com.nepu.ticket.model;

import java.time.DayOfWeek;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TicketDataCache {
	
	private ConcurrentHashMap<String, WeeklyTicketModel> ticketData =  null;
	
	public TicketDataCache(){
		ticketData =  new ConcurrentHashMap<String, WeeklyTicketModel>();
	}
	
	public void addTicket(Ticket ticket) {
		
		WeeklyTicketModel weeklyTicket = ticketData.get(ticket.getUser());
		if(weeklyTicket == null) {
			weeklyTicket =  new WeeklyTicketModel();
			ticketData.put(ticket.getUser(), weeklyTicket);
		}
		weeklyTicket.addTicket(ticket);
		
		
		
	}
	
	public WeeklyTicketModel getWeeklyTicketModel(String userID) {
		return  ticketData.get(userID);
	}
	
	public DailyTicketModel getDailyTicketModel(String userID,DayOfWeek dayOfWeek) {
		WeeklyTicketModel weeklyTicket = ticketData.get(userID);
		if(weeklyTicket == null) return null;
		return weeklyTicket.getDailyTicketModel(dayOfWeek);
	}

}
