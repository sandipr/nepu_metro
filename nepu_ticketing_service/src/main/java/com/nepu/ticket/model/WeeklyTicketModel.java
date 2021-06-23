package com.nepu.ticket.model;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WeeklyTicketModel {
	
	
	private ConcurrentHashMap<DayOfWeek, DailyTicketModel> ticketData =  null;
	
	private double weeklyRollup =  0;
	
	private double weeklyMaxCap =  0;
	
	

	
	public WeeklyTicketModel(){
		ticketData =  new ConcurrentHashMap<DayOfWeek, DailyTicketModel>();
		
	}
	
	public void addTicket(Ticket ticket) {
		DayOfWeek dayOfWeek = ticket.getBookingTime().getDayOfWeek();
		DailyTicketModel tickets = ticketData.get(dayOfWeek);
		if(tickets == null) {
			tickets = new DailyTicketModel();
			ticketData.put(ticket.getBookingTime().getDayOfWeek(), tickets);
		}
		tickets.addTicket(ticket);
		
	}
	
	public DailyTicketModel getDailyTicketModel(DayOfWeek dayOfWeek) {
		
		return ticketData.get(dayOfWeek);
		
	}
	
	public Collection<DailyTicketModel> getWeeklyTicketModel() {
		
		return ticketData.values();
	}

	public double getWeeklyRollup() {
		return weeklyRollup;
	}

	public void addToWeeklyRollup(double weeklyCap) {
		this.weeklyRollup += weeklyCap;
	}
	
	public void setWeeklyRollup(double weeklyCap) {
		this.weeklyRollup = weeklyCap;
	}
	
	public double getWeeklyMaxCap() {
		return weeklyMaxCap;
	}

	public void setWeeklyMaxCap(double weeklyMaxCap) {
			this.weeklyMaxCap = weeklyMaxCap;
	}

}
