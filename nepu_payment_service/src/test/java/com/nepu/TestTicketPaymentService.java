package com.nepu;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;

import com.nepu.service.PaymentService;
import com.nepu.service.PaymentServiceImpl;
import com.nepu.service.TicketManagementService;
import com.nepu.service.TicketManagementServiceImpl;
import com.nepu.ticket.model.TicketConfig;
import com.nepu.ticket.model.Ticket;

public class TestTicketPaymentService {

	@Test
	public void testZ1PeakTicketFareCalculation() {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		ticketManService.clearData();
		Ticket ticket = ticketManService.createTicket("Sandip", TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z1);

		LocalDateTime time = LocalDateTime.now().withDayOfMonth(21).withHour(8).withMinute(0);
		ticket.setBookingTime(time);
		PaymentService paymentService = PaymentServiceImpl.getInstance();
		paymentService.processPayment(ticket);
		assertEquals(30.0, ticket.getFare(), 0);

	}

	@Test
	public void testZ2PeakTicketFareCalculation() {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		ticketManService.clearData();
		Ticket ticket = ticketManService.createTicket("Sandip", TicketConfig.ZONE.Z2, TicketConfig.ZONE.Z2);
		LocalDateTime time = LocalDateTime.now().withDayOfMonth(21).withHour(8).withMinute(0);
		ticket.setBookingTime(time);

		PaymentService paymentService = PaymentServiceImpl.getInstance();

		paymentService.processPayment(ticket);
		//ticketManService.sentNotification(ticket);
		assertEquals(25.0, ticket.getFare(), 0);

	}

	@Test
	public void testZ1Z2PeakTicketFareCalculation() {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		ticketManService.clearData();
		Ticket ticket = ticketManService.createTicket("Sandip", TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z2);
		LocalDateTime time = LocalDateTime.now().withDayOfMonth(21).withHour(8).withMinute(0);
		ticket.setBookingTime(time);

		PaymentService paymentService = PaymentServiceImpl.getInstance();
		// paymentService.initService();

		paymentService.processPayment(ticket);
		//ticketManService.sentNotification(ticket);
		assertEquals(35.0, ticket.getFare(), 0);

	}

	@Test
	public void testZ1OffPeakTicketFareCalculation() {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		ticketManService.clearData();
		Ticket ticket = ticketManService.createTicket("Sandip", TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z1);
		LocalDateTime time = LocalDateTime.now().withDayOfMonth(21).withHour(6).withMinute(0);
		ticket.setBookingTime(time);

		PaymentService paymentService = PaymentServiceImpl.getInstance();
		// paymentService.initService();

		paymentService.processPayment(ticket);
		//ticketManService.sentNotification(ticket);
		assertEquals(25.0, ticket.getFare(), 0);

	}

	@Test
	public void testZ2OffPeakTicketFareCalculation() {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		ticketManService.clearData();
		Ticket ticket = ticketManService.createTicket("Sandip", TicketConfig.ZONE.Z2, TicketConfig.ZONE.Z2);
		LocalDateTime time = LocalDateTime.now().withDayOfMonth(21).withHour(6).withMinute(0);
		ticket.setBookingTime(time);

		PaymentService paymentService = PaymentServiceImpl.getInstance();
		// paymentService.initService();

		paymentService.processPayment(ticket);
		//ticketManService.sentNotification(ticket);
		assertEquals(20.0, ticket.getFare(), 0);

	}

	@Test
	public void testZ1Z2OffPeakTicketFareCalculation() {
		TicketManagementService ticketManService = TicketManagementServiceImpl.getService();
		ticketManService.clearData();
		Ticket ticket = ticketManService.createTicket("Sandip", TicketConfig.ZONE.Z1, TicketConfig.ZONE.Z2);
		LocalDateTime time = LocalDateTime.now().withDayOfMonth(21).withHour(6).withMinute(0);
		ticket.setBookingTime(time);

		PaymentService paymentService = PaymentServiceImpl.getInstance();
		paymentService.initService();

		paymentService.processPayment(ticket);
		//ticketManService.sentNotification(ticket);
		assertEquals(30.0, ticket.getFare(), 0);

	}

}
