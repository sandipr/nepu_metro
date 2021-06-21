package com.nepu.config;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleConfig {
	
	public static List<RuleTimeBased> peakDaysRange = new ArrayList<RuleTimeBased>();
	
	static {
		setPeakDays();
	}
	
	
	public static void setPeakDays(){
		
		if(peakDaysRange.size() >  0) 
			peakDaysRange.clear();
		
		RuleDateRange rangePeak = new RuleDateRange();
		rangePeak.setPeakRange(7,0,10,30);
		
		RuleDateRange rangePeakSecond = new RuleDateRange();
		rangePeakSecond.setPeakRange(17,0,20,0);
		
		RuleTimeBased timeBaseRuleMon = new RuleTimeBased();
		timeBaseRuleMon.setDayOfWeek(DayOfWeek.MONDAY);
		timeBaseRuleMon.addTimeRange(rangePeak);
		timeBaseRuleMon.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleMon);
		
		
		RuleTimeBased timeBaseRuleTue = new RuleTimeBased();
		timeBaseRuleTue.setDayOfWeek(DayOfWeek.TUESDAY);
		timeBaseRuleTue.addTimeRange(rangePeak);
		timeBaseRuleTue.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleTue);
		
		
		RuleTimeBased timeBaseRuleWed = new RuleTimeBased();
		timeBaseRuleWed.setDayOfWeek(DayOfWeek.WEDNESDAY);
		timeBaseRuleWed.addTimeRange(rangePeak);
		timeBaseRuleWed.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleWed);
		
		
		RuleTimeBased timeBaseRuleThu = new RuleTimeBased();
		timeBaseRuleThu.setDayOfWeek(DayOfWeek.THURSDAY);
		timeBaseRuleThu.addTimeRange(rangePeak);
		timeBaseRuleThu.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleThu);
		
		
		RuleTimeBased timeBaseRuleFri = new RuleTimeBased();
		timeBaseRuleFri.setDayOfWeek(DayOfWeek.FRIDAY);
		timeBaseRuleFri.addTimeRange(rangePeak);
		timeBaseRuleFri.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleFri);
		
		
		RuleDateRange rangePeakWeekend = new RuleDateRange();
		rangePeakWeekend.setPeakRange(9,0,11,0);
		
		RuleDateRange rangePeakSecondWekend = new RuleDateRange();
		rangePeakSecondWekend.setPeakRange(18,0,22,0);
		
		RuleTimeBased timeBaseRuleSat = new RuleTimeBased();
		timeBaseRuleSat.setDayOfWeek(DayOfWeek.SATURDAY);
		timeBaseRuleSat.addTimeRange(rangePeakWeekend);
		timeBaseRuleSat.addTimeRange(rangePeakSecondWekend);
		peakDaysRange.add(timeBaseRuleSat);
		
		
		RuleTimeBased timeBaseRuleSun = new RuleTimeBased();
		timeBaseRuleSun.setDayOfWeek(DayOfWeek.SUNDAY);
		timeBaseRuleSun.addTimeRange(rangePeakWeekend);
		timeBaseRuleSun.addTimeRange(rangePeakSecondWekend);
		peakDaysRange.add(timeBaseRuleSun);
		
	}
	
	public static List<RuleTimeBased> getTimeBaseRuleConfig(){
		return peakDaysRange;
	}


	public static double getZ1PeakfareSameZone() {
		return 30;
	}
	
	public static double getZ2PeakfareSameZone() {
		return 25;
	}
	
	public static double getZ1Z2PeakfareZone() {
		return 35;
	}
	
	
	public static double getZ1OffPeakfareSameZone() {
		return 25;
	}
	
	public static double getZ2OffPeakfareSameZone() {
		return 20;
	}
	
	public static double getZ1Z2OffPeakfareZone() {
		return 30;
	}
	
	
	public static double getDailyCapforZ1() {
		return 100;
	}
	
	public static double getDailyCapforZ2() {
		return 80;
	}
	
	public static double getDailyCapforZ1Z2() {
		return 120;
	}
	

}
