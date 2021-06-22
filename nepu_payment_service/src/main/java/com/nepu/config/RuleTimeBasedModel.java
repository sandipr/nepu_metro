package com.nepu.config;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class RuleTimeBasedModel {

	DayOfWeek dayOfWeek = null;

	List<RuleDateRangeModel> timeRange =  new ArrayList<RuleDateRangeModel>();


	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}


	public void addTimeRange(RuleDateRangeModel dateRange) {
		timeRange.add(dateRange);
	}

	public List<RuleDateRangeModel> getTimeRange() {
		return timeRange;
	}


}
