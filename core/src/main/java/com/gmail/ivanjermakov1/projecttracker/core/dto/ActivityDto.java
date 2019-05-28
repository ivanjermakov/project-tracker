package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;

import java.time.LocalDateTime;

public class ActivityDto {
	
	public Long id;
	public TaskDto task;
	public UserDto creator;
	public UserDto assignee;
	public TaskStatus status;
	public String description;
	public Double elapsed;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime timestamp;
	
	public ActivityDto() {
	}
	
	public TaskDto getTask() {
		return task;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTask(TaskDto task) {
		this.task = task;
	}
	
}
