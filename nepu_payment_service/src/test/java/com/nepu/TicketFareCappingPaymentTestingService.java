package com.nepu;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.nepu.service.PaymentService;
import com.nepu.service.PaymentServiceImpl;
import com.nepu.service.TicketManagementService;
import com.nepu.service.TicketManagementServiceImpl;
import com.nepu.ticket.model.TicketConfig;
import com.nepu.ticket.model.Ticket;

public class TicketFareCappingPaymentTestingService {
	
	
	
	@Test
    public void testZ1PeakTicketFareCalculation() {
		Ticket ticket1 =  createTicket(10,20,TicketConfig.ZONE.Z2,TicketConfig.ZONE.Z1);
		System.out.println("T1"+ticket1.getFare());
		
		Ticket ticket2 =  createTicket(10,45,TicketConfig.ZONE.Z1,TicketConfig.ZONE.Z1);
		System.out.println("T2"+ticket2.getFare());
		
		Ticket ticket3 =  createTicket(16,15,TicketConfig.ZONE.Z1,TicketConfig.ZONE.Z1);
		System.out.println("T3"+ticket3.getFare());
		
		Ticket ticket4 =  createTicket(18,15,TicketConfig.ZONE.Z1,TicketConfig.ZONE.Z1);
		System.out.println("T4"+ticket4.getFare());
		
		Ticket ticket5 = createTicket(19,00,TicketConfig.ZONE.Z1,TicketConfig.ZONE.Z2);
		System.out.println("T5"+ticket5.getFare());
		
		
		assertEquals(5.0, ticket5.getFare(), 0);
		
    }
	
	public Ticket createTicket(int hr, int min, TicketConfig.ZONE startZone, TicketConfig.ZONE endZone) {
		TicketManagementService ticketManService =  TicketManagementServiceImpl.getService();
		Ticket ticket = ticketManService.createTicket("Sandip", startZone, endZone);
		LocalDateTime time =  LocalDateTime.now().withDayOfMonth(21).withHour(hr).withMinute(min);
		ticket.setBookingTime(time);
		PaymentService paymentService =  PaymentServiceImpl.getInstance();

		paymentService.processPayment(ticket);
		ticketManService.sentNotification(ticket);
		return ticket;
	}
	
	

}
