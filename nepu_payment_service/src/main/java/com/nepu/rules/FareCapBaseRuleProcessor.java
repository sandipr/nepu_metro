package com.nepu.rules;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.nepu.config.RuleMetaData;
import com.nepu.constants.Config;
import com.nepu.service.TicketManagementService;
import com.nepu.service.TicketManagementServiceImpl;
import com.nepu.ticket.model.DailyTicketModel;
import com.nepu.ticket.model.Ticket;
import com.nepu.ticket.model.TicketConfig;
import com.nepu.ticket.model.WeeklyTicketModel;


/*
 * Implement logic for Fare Cap base rule
 */

public class FareCapBaseRuleProcessor implements Rules{

	@Override
	public void initRule(Config config) {

	}

	@Override
	public void applyRule(Ticket ticket) throws Exception {
		TicketManagementService ticketManService =  TicketManagementServiceImpl.getService();
		
		DailyTicketModel dailyModel = ticketManService.getDailyTicketModel(ticket.getUser(), ticket.getBookingTime().getDayOfWeek());
			
		WeeklyTicketModel weeklyModel = ticketManService.getWeekyTicketModel(ticket.getUser());

		checkDailyCap(ticket,dailyModel, weeklyModel);

	}

	private void checkDailyCap(Ticket currentTicket,DailyTicketModel dailyTicketModel, WeeklyTicketModel weeklyModel ) {
				
		//weekly cap reached
		if( weeklyModel.getWeeklyRollup() > 0 && weeklyModel.getWeeklyRollup() >= weeklyModel.getWeeklyMaxCap()) {
			currentTicket.setFare( 0);
			return;
		}
		
		//daily cap reached
		if(dailyTicketModel.getDailyRollup() > 0 && dailyTicketModel.getDailyRollup() >= dailyTicketModel.getDailyMaxCap()) {
			currentTicket.setFare( 0);
			return;
		}
		
		// find the max cap for daily and weekly
		double dailyMaxCap = 0.0d;
		double weeklyMaxCap = 0.0d;
		if(currentTicket.getStartZone() == currentTicket.getEndZOne() && currentTicket.getStartZone() == TicketConfig.ZONE.Z2 ) {
			dailyMaxCap = RuleMetaData.getDailyCapforZ1();
			weeklyMaxCap = RuleMetaData.getWeeklyCapforZ1();
		}
		if(currentTicket.getStartZone() == currentTicket.getEndZOne() && currentTicket.getStartZone() == TicketConfig.ZONE.Z1 ) {
			dailyMaxCap = RuleMetaData.getDailyCapforZ2();
			weeklyMaxCap = RuleMetaData.getWeeklyCapforZ1();
		}
		else {
			dailyMaxCap = RuleMetaData.getDailyCapforZ1Z2();
			weeklyMaxCap = RuleMetaData.getWeeklyCapforZ1Z2();
		}
		//set max daily and weekly cap
		if( dailyMaxCap > dailyTicketModel.getDailyMaxCap() ) {
			dailyTicketModel.setDailyMaxCap(dailyMaxCap);
			
		}
		if( weeklyMaxCap > weeklyModel.getWeeklyMaxCap() ) {
			weeklyModel.setWeeklyMaxCap(weeklyMaxCap);
			
		}
		
		dailyTicketModel.addToDailyRollup(currentTicket.getFare());
		
		if(dailyTicketModel.getDailyRollup() >= dailyTicketModel.getDailyMaxCap()) {
			currentTicket.setFare(dailyTicketModel.getDailyMaxCap()  - (dailyTicketModel.getDailyRollup() - currentTicket.getFare()));
			dailyTicketModel.setDailyRollup(dailyTicketModel.getDailyMaxCap());
			
		}
		
		weeklyModel.addToWeeklyRollup(currentTicket.getFare());
		
		if( weeklyModel.getWeeklyRollup() >= weeklyModel.getWeeklyMaxCap()) {
			currentTicket.setFare(weeklyModel.getWeeklyMaxCap() - ( weeklyModel.getWeeklyRollup() - currentTicket.getFare()));
			weeklyModel.setWeeklyRollup(weeklyModel.getWeeklyMaxCap());
			
		}
	
	}
	
	
}
