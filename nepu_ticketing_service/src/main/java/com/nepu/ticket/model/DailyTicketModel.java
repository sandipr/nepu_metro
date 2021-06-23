package com.nepu.ticket.model;

import java.util.ArrayList;
import java.util.List;

public class DailyTicketModel {
	
	
	private List<Ticket> ticketData =  null;
	
	double dailyMaxCap =  0.0d;
	
	double dailyRollup =  0.0d;
	

	public DailyTicketModel(){
		
		ticketData = new ArrayList<Ticket>();
	}
	

	public double getDailyMaxCap() {
		return dailyMaxCap;
	}

	public void setDailyMaxCap(double dailyMaxCap) {
		this.dailyMaxCap = dailyMaxCap;
	}

	
	public void addTicket(Ticket ticket) {
		ticketData.add(ticket);
	}
	
	public List<Ticket> getDailyTickets(){
		return ticketData;
	}
	
	public double getDailyRollup() {
		return dailyRollup;
	}


	public void addToDailyRollup(double dailyRollup) {
		this.dailyRollup += dailyRollup;
	}

	public void setDailyRollup(double dailyRollup) {
		this.dailyRollup = dailyRollup;
	}


}
