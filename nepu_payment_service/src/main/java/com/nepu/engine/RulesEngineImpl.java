package com.nepu.engine;

import java.util.ArrayList;
import java.util.List;

import com.nepu.rules.FareCapRule;
import com.nepu.rules.Rules;
import com.nepu.rules.TimeRule;
import com.nepu.ticket.model.Ticket;

public class RulesEngineImpl implements RulesEngine{

	List<Rules> listOfRules =  new ArrayList<Rules>();

	public boolean start() {
		TimeRule timeRule = new TimeRule();
		timeRule.initRule(null);
		listOfRules.add(timeRule);

		FareCapRule capRule = new FareCapRule();
		capRule.initRule(null);
		listOfRules.add(capRule);

		return false;
	}

	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}


	public void process(Ticket ticket) throws Exception {

		for(Rules rule: listOfRules) {
			rule.applyRule(ticket);
		}
	}


}
