package com.nepu.config;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class should be populated from MDS Service.
 * Dummy implementation for simplicity
 */
public class RuleMetaData {

	public static List<RuleTimeBasedModel> peakDaysRange = new ArrayList<RuleTimeBasedModel>();

	static {
		setPeakDays();
	}

	public static void setPeakDays() {

		if (peakDaysRange.size() > 0)
			peakDaysRange.clear();

		RuleDateRangeModel rangePeak = new RuleDateRangeModel();
		rangePeak.setPeakRange(7, 0, 10, 30);

		RuleDateRangeModel rangePeakSecond = new RuleDateRangeModel();
		rangePeakSecond.setPeakRange(17, 0, 20, 0);

		RuleTimeBasedModel timeBaseRuleMon = new RuleTimeBasedModel();
		timeBaseRuleMon.setDayOfWeek(DayOfWeek.MONDAY);
		timeBaseRuleMon.addTimeRange(rangePeak);
		timeBaseRuleMon.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleMon);

		RuleTimeBasedModel timeBaseRuleTue = new RuleTimeBasedModel();
		timeBaseRuleTue.setDayOfWeek(DayOfWeek.TUESDAY);
		timeBaseRuleTue.addTimeRange(rangePeak);
		timeBaseRuleTue.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleTue);

		RuleTimeBasedModel timeBaseRuleWed = new RuleTimeBasedModel();
		timeBaseRuleWed.setDayOfWeek(DayOfWeek.WEDNESDAY);
		timeBaseRuleWed.addTimeRange(rangePeak);
		timeBaseRuleWed.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleWed);

		RuleTimeBasedModel timeBaseRuleThu = new RuleTimeBasedModel();
		timeBaseRuleThu.setDayOfWeek(DayOfWeek.THURSDAY);
		timeBaseRuleThu.addTimeRange(rangePeak);
		timeBaseRuleThu.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleThu);

		RuleTimeBasedModel timeBaseRuleFri = new RuleTimeBasedModel();
		timeBaseRuleFri.setDayOfWeek(DayOfWeek.FRIDAY);
		timeBaseRuleFri.addTimeRange(rangePeak);
		timeBaseRuleFri.addTimeRange(rangePeakSecond);
		peakDaysRange.add(timeBaseRuleFri);

		RuleDateRangeModel rangePeakWeekend = new RuleDateRangeModel();
		rangePeakWeekend.setPeakRange(9, 0, 11, 0);

		RuleDateRangeModel rangePeakSecondWekend = new RuleDateRangeModel();
		rangePeakSecondWekend.setPeakRange(18, 0, 22, 0);

		RuleTimeBasedModel timeBaseRuleSat = new RuleTimeBasedModel();
		timeBaseRuleSat.setDayOfWeek(DayOfWeek.SATURDAY);
		timeBaseRuleSat.addTimeRange(rangePeakWeekend);
		timeBaseRuleSat.addTimeRange(rangePeakSecondWekend);
		peakDaysRange.add(timeBaseRuleSat);

		RuleTimeBasedModel timeBaseRuleSun = new RuleTimeBasedModel();
		timeBaseRuleSun.setDayOfWeek(DayOfWeek.SUNDAY);
		timeBaseRuleSun.addTimeRange(rangePeakWeekend);
		timeBaseRuleSun.addTimeRange(rangePeakSecondWekend);
		peakDaysRange.add(timeBaseRuleSun);

	}

	public static List<RuleTimeBasedModel> getTimeBaseRuleConfig() {
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
