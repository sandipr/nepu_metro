package com.nepu.service;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import com.nepu.ticket.model.TicketConfig;
import com.nepu.ticket.model.WeeklyTicketModel;
import com.nepu.ticket.model.DailyTicketModel;
import com.nepu.ticket.model.Ticket;

public interface TicketManagementService {
	
	public Ticket createTicket(String userID, LocalDateTime time  ,TicketConfig.ZONE startZone,TicketConfig.ZONE endZOne);
	
	
	public void sentNotification(Ticket ticket);
	
	public void deleteTicket(Ticket ticket);

	void clearData();

	DailyTicketModel getDailyTicketModel(String userID, DayOfWeek dayOfWeek);


	WeeklyTicketModel getWeekyTicketModel(String userID);

}
