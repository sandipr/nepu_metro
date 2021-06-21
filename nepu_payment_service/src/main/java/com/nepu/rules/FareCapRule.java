package com.nepu.rules;

import java.util.List;

import com.nepu.config.RuleConfig;
import com.nepu.constants.Config;
import com.nepu.service.TicketManagementService;
import com.nepu.service.TicketManagementServiceImpl;
import com.nepu.ticket.model.Ticket;
import com.nepu.ticket.model.TicketConfig;

public class FareCapRule implements Rules{

	@Override
	public void initRule(Config config) {

	}

	@Override
	public void applyRule(Ticket ticket) throws Exception {
		TicketManagementService ticketManService =  TicketManagementServiceImpl.getService();
		List<Ticket> allTickets = ticketManService.loadAllTickets(ticket.getUser());
		checkDailyCap(ticket,allTickets);



	}

	private void checkDailyCap(Ticket currentTicket, List<Ticket> allTickets) {
		double totalDailyFareZ1 = 0.0d;
		double totalDailyFareZ2 = 0.0d;
		double totalDailyFareZ1Z2 = 0.0d;

		if(allTickets == null)
			return;


		for(Ticket dailyTicket : allTickets) {
			if(dailyTicket.getBookingTime().getDayOfYear() == currentTicket.getBookingTime().getDayOfYear()) {

				if(dailyTicket.getStartZone() == TicketConfig.ZONE.Z1 && dailyTicket.getEndZOne() == TicketConfig.ZONE.Z1 )
					totalDailyFareZ1 += dailyTicket.getFare();

				if(dailyTicket.getStartZone() == TicketConfig.ZONE.Z2 && dailyTicket.getEndZOne() == TicketConfig.ZONE.Z2 )
					totalDailyFareZ2 += dailyTicket.getFare();


				if(dailyTicket.getStartZone() != dailyTicket.getEndZOne()   )
					totalDailyFareZ1Z2 += dailyTicket.getFare();

			}

		}
		double currentfare = currentTicket.getFare();

		double totalMaxFare = totalDailyFareZ1 + totalDailyFareZ2 + totalDailyFareZ1Z2 + currentfare;

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

		if(totalMaxFare >=  RuleConfig.getDailyCapforZ1Z2() )
			currentTicket.setFare( Math.abs(RuleConfig.getDailyCapforZ1Z2()  - (totalMaxFare - currentTicket.getFare())));

		else if(totalZ1MaxFare >=  RuleConfig.getDailyCapforZ1() )
			currentTicket.setFare( Math.abs(RuleConfig.getDailyCapforZ1()  - (totalMaxFare - currentTicket.getFare())));

		else if(totalZ2MaxFare >=  RuleConfig.getDailyCapforZ2() )
			currentTicket.setFare( Math.abs(RuleConfig.getDailyCapforZ2()  - (totalMaxFare - currentTicket.getFare())));


	}




}
