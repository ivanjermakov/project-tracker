package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;

public class NewActivityDto {
	
	public Long taskId;
	public TaskStatus taskStatus;
	public String description;
	public Double completionDifference;
	
	public NewActivityDto() {
	}
	
	
	
}
