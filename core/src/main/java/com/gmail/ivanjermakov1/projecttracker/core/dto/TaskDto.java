package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
	
	public ActivityDto lastActivity;

	public UserDto assignee;
	
	public TaskDto() {
	}
	
	public TaskDto compute() {
		subtasks.forEach(TaskDto::compute);
		
		elapsed = elapsed != null ? elapsed : 0d;
		elapsed += subtasks
				.stream()
				.filter(s -> Objects.nonNull(s.elapsed))
				.mapToDouble(s -> s.elapsed)
				.sum();
		elapsed = elapsed != 0 ? elapsed : null;
		
		estimate = estimate != null ? estimate : 0d;
		estimate += subtasks
				.stream()
				.filter(s -> Objects.nonNull(s.estimate))
				.mapToDouble(s -> s.estimate)
				.sum();
		estimate = estimate != 0 ? estimate : null;
		
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectDto getProject() {
		return project;
	}

	public void setProject(ProjectDto project) {
		this.project = project;
	}

	public UserDto getCreator() {
		return creator;
	}

	public void setCreator(UserDto creator) {
		this.creator = creator;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public Double getEstimate() {
		return estimate;
	}

	public void setEstimate(Double estimate) {
		this.estimate = estimate;
	}

	public Double getElapsed() {
		return elapsed;
	}

	public void setElapsed(Double elapsed) {
		this.elapsed = elapsed;
	}

	public LocalDateTime getOpened() {
		return opened;
	}

	public void setOpened(LocalDateTime opened) {
		this.opened = opened;
	}

	public LocalDate getDue() {
		return due;
	}

	public void setDue(LocalDate due) {
		this.due = due;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TaskDto> getSubtasks() {
		return subtasks;
	}

	public void setSubtasks(List<TaskDto> subtasks) {
		this.subtasks = subtasks;
	}

	public Long getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(Long parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public ActivityDto getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(ActivityDto lastActivity) {
		this.lastActivity = lastActivity;
	}

	public UserDto getAssignee() {
		return assignee;
	}

	public void setAssignee(UserDto assignee) {
		this.assignee = assignee;
	}
}
