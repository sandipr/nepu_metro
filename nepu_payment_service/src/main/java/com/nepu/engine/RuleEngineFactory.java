package com.nepu.engine;

import com.nepu.constants.Config;

public class RuleEngineFactory {
	
	public static RulesEngine getEngine(Config config) {
		//if(config)
		return new RulesEngineImpl();
		
		
		
	}

}
