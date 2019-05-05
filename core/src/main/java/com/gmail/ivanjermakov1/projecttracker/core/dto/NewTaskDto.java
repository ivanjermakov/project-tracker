package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NewTaskDto {
	
	public Long parentTaskId;
	
	@NotNull
	public Long projectId;
	
	@NotNull
	public TaskType type;
	
	public Double estimate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate due;
	
	@NotBlank
	public String name;
	
	public String description;
	
	public NewTaskDto() {
	}
	
	public NewTaskDto(Long parentTaskId, Long projectId, TaskType type, Double estimate, LocalDate due, String name, String description) {
		this.parentTaskId = parentTaskId;
		this.projectId = projectId;
		this.type = type;
		this.estimate = estimate;
		this.due = due;
		this.name = name;
		this.description = description;
	}
	
}
