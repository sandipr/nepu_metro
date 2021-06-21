package com.nepu.config;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class RuleTimeBased {

	DayOfWeek dayOfWeek = null;

	List<RuleDateRange> timeRange =  new ArrayList<RuleDateRange>();


	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}


	public void addTimeRange(RuleDateRange dateRange) {
		timeRange.add(dateRange);
	}

	public List<RuleDateRange> getTimeRange() {
		return timeRange;
	}


}
