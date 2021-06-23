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
		//double totalDailyFareZ1 = 0.0d;
		//double totalDailyFareZ2 = 0.0d;
		//double totalDailyFareZ1Z2 = 0.0d;

		/*if(dailyTicketModel == null)
			return;*/
		
		/*List<Ticket> allDailyTickets =  dailyTicketModel.getDailyTickets();


		for(Ticket dailyTicket : allDailyTickets) {
			if(dailyTicket.getBookingTime().getDayOfYear() == currentTicket.getBookingTime().getDayOfYear()) {

				if(dailyTicket.getStartZone() == TicketConfig.ZONE.Z1 && dailyTicket.getEndZOne() == TicketConfig.ZONE.Z1 )
					totalDailyFareZ1 += dailyTicket.getFare();

				if(dailyTicket.getStartZone() == TicketConfig.ZONE.Z2 && dailyTicket.getEndZOne() == TicketConfig.ZONE.Z2 )
					totalDailyFareZ2 += dailyTicket.getFare();


				if(dailyTicket.getStartZone() != dailyTicket.getEndZOne()   )
					totalDailyFareZ1Z2 += dailyTicket.getFare();

			}

		}*/
		//double currentfare = currentTicket.getFare();
		
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
			//currentTicket.setFare(dailyTicketModel.getDailyRollup() - dailyTicketModel.getDailyMaxCap());
			currentTicket.setFare(dailyTicketModel.getDailyMaxCap()  - (dailyTicketModel.getDailyRollup() - currentTicket.getFare()));
			dailyTicketModel.setDailyRollup(dailyTicketModel.getDailyMaxCap());
			
		}
		
		weeklyModel.addToWeeklyRollup(currentTicket.getFare());
		
		if( weeklyModel.getWeeklyRollup() >= weeklyModel.getWeeklyMaxCap()) {
			//currentTicket.setFare(weeklyModel.getWeeklyRollup() - weeklyModel.getWeeklyMaxCap());
			currentTicket.setFare(weeklyModel.getWeeklyMaxCap() - ( weeklyModel.getWeeklyRollup() - currentTicket.getFare()));
			weeklyModel.setWeeklyRollup(weeklyModel.getWeeklyMaxCap());
			
		}
		
		
	/*	currentTicket.setFare( Math.abs(dailyMaxCap  - (dailyTicketModel.getDailyCap() + currentTicket.getFare())));
		

		double totalMaxFare = dailyTicketModel.getDailyCap(); //totalDailyFareZ1 + totalDailyFareZ2 + totalDailyFareZ1Z2 + currentfare;
		
		

		double totalZ1MaxFare = totalDailyFareZ1  ;

		double totalZ2MaxFare = totalDailyFareZ2  ;

		if(currentTicket.getStartZone() == currentTicket.getEndZOne() && currentTicket.getStartZone() == TicketConfig.ZONE.Z1 )
			totalZ1MaxFare = totalZ1MaxFare + currentTicket.getFare();

		if(currentTicket.getStartZone() == currentTicket.getEndZOne() && currentTicket.getStartZone() == TicketConfig.ZONE.Z2 )
			totalZ2MaxFare = totalZ2MaxFare + currentTicket.getFare();

		if(currentTicket.getStartZone() != currentTicket.getEndZOne()  ) {

			totalZ1MaxFare = totalZ1MaxFare + currentTicket.getFare();
			totalZ2MaxFare = totalZ2MaxFare + currentTicket.getFare();
		}

		if(totalMaxFare >=  RuleMetaData.getDailyCapforZ1Z2() ) {
			currentTicket.setFare( Math.abs(RuleMetaData.getDailyCapforZ1Z2()  - (totalMaxFare - currentTicket.getFare())));
			dailyTicketModel.setDailyMaxCap(RuleMetaData.getDailyCapforZ1Z2());
			weeklyModel.addToWeeklyCap(dailyTicketModel.getDailyMaxCap());
			weeklyModel.setWeeklyMaxCap(RuleMetaData.getWeeklyCapforZ1Z2());
		}

		else if(totalZ1MaxFare >=  RuleMetaData.getDailyCapforZ1() ) {
			currentTicket.setFare( Math.abs(RuleMetaData.getDailyCapforZ1()  - (totalMaxFare - currentTicket.getFare())));
			dailyTicketModel.setDailyMaxCap(RuleMetaData.getDailyCapforZ1());
			weeklyModel.addToWeeklyCap(RuleMetaData.getWeeklyCapforZ1());
		}

		else if(totalZ2MaxFare >=  RuleMetaData.getDailyCapforZ2() ) {
			currentTicket.setFare( Math.abs(RuleMetaData.getDailyCapforZ2()  - (totalMaxFare - currentTicket.getFare())));
			dailyTicketModel.setDailyMaxCap(RuleMetaData.getDailyCapforZ2());
			weeklyModel.addToWeeklyCap(RuleMetaData.getWeeklyCapforZ2());
		} */

	}

	
	
}
