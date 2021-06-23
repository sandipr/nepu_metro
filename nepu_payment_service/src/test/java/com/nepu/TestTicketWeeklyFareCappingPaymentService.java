package com.nepu;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.plaf.synth.SynthScrollBarUI;

import org.junit.Test;

import com.nepu.service.PaymentService;
import com.nepu.service.PaymentServiceImpl;
import com.nepu.service.TicketManagementService;
import com.nepu.service.TicketManagementServiceImpl;
import com.nepu.ticket.model.TicketConfig;
import com.nepu.ticket.model.WeeklyTicketModel;
import com.nepu.ticket.model.DailyTicketModel;
import com.nepu.ticket.model.Ticket;

public class TestTicketWeeklyFareCappingPaymentService {

	
	@Test
	public void testWeeklyPeakTicketFareCalculation() {
		
		createDailyTikcet(21);
		createDailyTikcet(22);
		createDailyTikcet(23);
		createDailyTikcet(24);
		createDailyTikcet(25);
		printWeeklyData(createDailyTikcet(26));

	}

	
	public Ticket createDailyTikcet(int day) {
		createTicket(day, 10, 20, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z2);
		createTicket(day, 10, 45, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z2);
		createTicket(day, 16, 15, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z2);
		createTicket(day, 18, 15, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z2);
		return createTicket(day, 19, 00, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z2);

	}
	

	public Ticket createTicket(int day, int hr, int min, TicketConfig.ZONE startZone, TicketConfig.ZONE endZone) {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		LocalDateTime time = LocalDateTime.now().withDayOfMonth(day).withHour(hr).withMinute(min);
		Ticket ticket = ticketManService.createTicket("Sandip", time, startZone, endZone);

		PaymentService paymentService = PaymentServiceImpl.getInstance();

		paymentService.processPayment(ticket);
		ticketManService.sentNotification(ticket);
		return ticket;
	}
	
	public void printWeeklyData(Ticket ticket) {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		DailyTicketModel dailyModel = ticketManService.getDailyTicketModel(ticket.getUser(), ticket.getBookingTime().getDayOfWeek());
		WeeklyTicketModel weeklyModel = ticketManService.getWeekyTicketModel(ticket.getUser());
		assertEquals(dailyModel.getDailyMaxCap(), 0,0);
		assertEquals(dailyModel.getDailyRollup(), 0,0);
	}

}
