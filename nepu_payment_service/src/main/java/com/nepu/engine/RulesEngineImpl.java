package com.nepu.engine;

import java.util.ArrayList;
import java.util.List;

import com.nepu.rules.FareCapBaseRuleProcessor;
import com.nepu.rules.Rules;
import com.nepu.rules.TimeBaseRuleProcessor;
import com.nepu.ticket.model.Ticket;

/*
 * Default Rule engine, USes Time and Fare based rule processor
 */

public class RulesEngineImpl implements RulesEngine{

	List<Rules> listOfRules =  new ArrayList<Rules>();

	/*
	 * Shpuld come from configuration - rule defination
	 */
	public boolean start() {
		TimeBaseRuleProcessor timeRule = new TimeBaseRuleProcessor();
		timeRule.initRule(null);
		listOfRules.add(timeRule);

		FareCapBaseRuleProcessor capRule = new FareCapBaseRuleProcessor();
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
