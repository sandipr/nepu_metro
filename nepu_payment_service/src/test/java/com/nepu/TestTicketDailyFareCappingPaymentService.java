package com.nepu;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.nepu.service.PaymentService;
import com.nepu.service.PaymentServiceImpl;
import com.nepu.service.TicketManagementService;
import com.nepu.service.TicketManagementServiceImpl;
import com.nepu.ticket.model.Ticket;
import com.nepu.ticket.model.TicketConfig;

public class TestTicketDailyFareCappingPaymentService {

	@Test
	public void testZ1PeakTicketFareCalculation() {
		Ticket ticket1 = createTicket(10, 20, TicketConfig.ZONE.Z2, TicketConfig.ZONE.Z1);
		System.out.println(ticket1.getFare());

		Ticket ticket2 = createTicket(10, 45, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z1);
		System.out.println(ticket2.getFare());
		
		Ticket ticket3 = createTicket(16, 15, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z1);
		System.out.println(ticket3.getFare());
		
		Ticket ticket4 = createTicket(18, 15, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z1);
		System.out.println(ticket4.getFare());
		
		Ticket ticket5 = createTicket(19, 00, TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z2);
		System.out.println(ticket5.getFare());
		assertEquals(5.0, ticket5.getFare(), 0);

	}

	public Ticket createTicket(int hr, int min, TicketConfig.ZONE startZone, TicketConfig.ZONE endZone) {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		LocalDateTime time = LocalDateTime.now().withDayOfMonth(21).withHour(hr).withMinute(min);
		Ticket ticket = ticketManService.createTicket("Sandip",time, startZone, endZone);

		PaymentService paymentService = PaymentServiceImpl.getInstance();

		paymentService.processPayment(ticket);
		ticketManService.sentNotification(ticket);
		return ticket;
	}

}
