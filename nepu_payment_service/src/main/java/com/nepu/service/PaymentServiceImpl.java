package com.nepu.service;

import com.nepu.engine.RuleEngineFactory;
import com.nepu.engine.RulesEngine;
import com.nepu.ticket.model.Ticket;

public class PaymentServiceImpl implements PaymentService{
	
	static PaymentService paymentService = new PaymentServiceImpl();
	
	RulesEngine engine = null;
	
	private PaymentServiceImpl() {
		initService();
	}
	
	public static PaymentService getInstance() {
		return paymentService;
	}


	@Override
	public void processPayment(Ticket ticket) {
		try {
			engine.process(ticket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(ticket.getFare());
		//PaymentEngine.process paymen
		//send notification
	}

	@Override
	public void initService() {
		engine = RuleEngineFactory.getEngine(null);
		engine.start();
		
	}
	

}
