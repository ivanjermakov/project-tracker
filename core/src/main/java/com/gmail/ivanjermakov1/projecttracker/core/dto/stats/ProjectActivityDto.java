package com.gmail.ivanjermakov1.projecttracker.core.dto.stats;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ProjectActivityDto {
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate day;
	
	public Integer activityAmount;
	
	public ProjectActivityDto() {
	}
	
	public ProjectActivityDto(LocalDate day, Integer activityAmount) {
		this.day = day;
		this.activityAmount = activityAmount;
	}
	
}
