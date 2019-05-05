package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TaskDto {
	
	public Long id;
	public ProjectDto project;
	public UserDto creator;
	public TaskType type;
	public Double estimate;
	public Double elapsed;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime opened;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate due;
	
	public String name;
	public String description;
	public List<TaskDto> subtasks;
	public Long parentTaskId;
	
	public TaskDto() {
	}
	
	public TaskDto(Long id, ProjectDto project, UserDto creator, TaskType type, Double estimate, Double elapsed, LocalDateTime opened, LocalDate due, String name, String description, List<TaskDto> subtasks) {
		this.id = id;
		this.project = project;
		this.creator = creator;
		this.type = type;
		this.estimate = estimate;
		this.elapsed = elapsed;
		this.opened = opened;
		this.due = due;
		this.name = name;
		this.description = description;
		this.subtasks = subtasks;
	}
	
	public void setParentTaskId(Long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}
	
}
