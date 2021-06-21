package com.nepu.service;

import com.nepu.ticket.model.Ticket;

public interface PaymentService {
	
	public void initService();
	
	public void processPayment(Ticket ticket);

}
