package com.nepu.config;

import java.time.LocalDateTime;

public class RuleDateRangeModel {
	
	LocalDateTime peakStartTime =  LocalDateTime.now();
	
	LocalDateTime peakEndTime =  LocalDateTime.now();
	
	
	public LocalDateTime getPeakStartTime() {
		return peakStartTime;
	}

	public void setPeakStartTime(LocalDateTime peakStartTime) {
		this.peakStartTime = peakStartTime;
	}

	public LocalDateTime getPeakEndTime() {
		return peakEndTime;
	}

	public void setPeakEndTime(LocalDateTime peakEndTime) {
		this.peakEndTime = peakEndTime;
	}


	
	public void setPeakRange(int peakHrStart, int peakMinStart, int peakHrEndt, int peakMinEnd) {
		peakStartTime = peakStartTime.withHour(peakHrStart);
		peakStartTime = peakStartTime.withMinute(peakMinStart);
		
		peakEndTime = peakEndTime.withHour(peakHrEndt);
		peakEndTime = peakEndTime.withMinute(peakMinEnd);
		
		
	}
}
