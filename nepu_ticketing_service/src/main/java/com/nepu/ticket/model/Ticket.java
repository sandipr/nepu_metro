package com.nepu.ticket.model;

import java.time.LocalDateTime;

public class Ticket {
	
	
	
	private String user = null;
	
	private double fare;
	
	private TicketConfig.ZONE startZone;
	private TicketConfig.ZONE endZOne;
	
	private LocalDateTime  bookingTime =  null;
	
	public Ticket(String user,TicketConfig.ZONE startZone, TicketConfig.ZONE endZOne){
		this.user = user;
		this.startZone = startZone;
		this.endZOne = endZOne;
		bookingTime = LocalDateTime.now();
	}
	
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public TicketConfig.ZONE getStartZone() {
		return startZone;
	}
	public void setStartZone(TicketConfig.ZONE startZone) {
		this.startZone = startZone;
	}
	public TicketConfig.ZONE getEndZOne() {
		return endZOne;
	}
	public void setEndZOne(TicketConfig.ZONE endZOne) {
		this.endZOne = endZOne;
	}
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}
	

}
