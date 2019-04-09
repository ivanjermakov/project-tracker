package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;

import java.time.LocalDateTime;

public class NewTaskDto {
	
	public Long parentTaskId;
	public Long projectId;
	public TaskType type;
	public Double estimate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime due;
	public String name;
	public String description;
	
	public NewTaskDto() {
	}
	
	public NewTaskDto(Long parentTaskId, Long projectId, TaskType type, Double estimate, LocalDateTime due, String name, String description) {
		this.parentTaskId = parentTaskId;
		this.projectId = projectId;
		this.type = type;
		this.estimate = estimate;
		this.due = due;
		this.name = name;
		this.description = description;
	}
	
}
