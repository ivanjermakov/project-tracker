package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NewActivityDto {
	
	@NotNull
	public Long taskId;
	
	public TaskStatus status;
	public String description;
	
	@Min(value = 0, message = "elapsed time cannot be negative")
	public Double elapsed;
	
	public String assigneeLogin;
	
	public NewActivityDto() {
	}
	
	
}
