package com.nepu.rules;

import java.time.LocalDateTime;
import java.util.List;

import com.nepu.config.RuleConfig;
import com.nepu.config.RuleDateRange;
import com.nepu.config.RuleTimeBased;
import com.nepu.constants.Config;
import com.nepu.ticket.model.Ticket;
import com.nepu.ticket.model.TicketConfig;


public class TimeRule implements Rules {

	public void initRule(Config config) {
		//RuleConfig.setPeakDays();
	}

	public void applyRule(Ticket ticket) throws Exception {

		LocalDateTime bookingTime = ticket.getBookingTime();

		List<RuleTimeBased> timeRules = RuleConfig.getTimeBaseRuleConfig();

		for (RuleTimeBased timeRule : timeRules) {

			if (timeRule.getDayOfWeek() == bookingTime.getDayOfWeek()) {

				List<RuleDateRange> peakRange = timeRule.getTimeRange();

				LocalDateTime ticketTime = LocalDateTime.now().withHour(bookingTime.getHour())
						.withMinute(bookingTime.getMinute()).withSecond(0);
				ticket.setFare(getOffPeakFareBasedOnZone(ticket));
				
				for (RuleDateRange timeRange : peakRange) {

					if (within(ticketTime, timeRange.getPeakStartTime(), timeRange.getPeakEndTime())) {
						ticket.setFare(getPeakFareBasedOnZone(ticket));
						break;
					}
				}
				break;
			}
		}

	}

	private boolean within(LocalDateTime toCheck, LocalDateTime startInterval, LocalDateTime endInterval) {
		return toCheck.compareTo(startInterval) >= 0 && toCheck.compareTo(endInterval) <= 0;
	}
	
	private double getPeakFareBasedOnZone(Ticket ticket) {
		
		if((ticket.getStartZone()  == ticket.getEndZOne()) && ticket.getStartZone() == TicketConfig.ZONE.Z1)
			return RuleConfig.getZ1PeakfareSameZone();
		
		if((ticket.getStartZone()  == ticket.getEndZOne()) && ticket.getStartZone() == TicketConfig.ZONE.Z2)
			return RuleConfig.getZ2PeakfareSameZone();
		
		if((ticket.getStartZone()  != ticket.getEndZOne()) )
			return RuleConfig.getZ1Z2PeakfareZone();
		
		
		return 0.0;
	}

	
	private double getOffPeakFareBasedOnZone(Ticket ticket) {
		
		if((ticket.getStartZone()  == ticket.getEndZOne()) && ticket.getStartZone() == TicketConfig.ZONE.Z1)
			return RuleConfig.getZ1OffPeakfareSameZone();
		
		if((ticket.getStartZone()  == ticket.getEndZOne()) && ticket.getStartZone() == TicketConfig.ZONE.Z2)
			return RuleConfig.getZ2OffPeakfareSameZone();
		
		if((ticket.getStartZone()  != ticket.getEndZOne()))
			return RuleConfig.getZ1Z2OffPeakfareZone();
		

		
		return 0.0;
	}
	

}
