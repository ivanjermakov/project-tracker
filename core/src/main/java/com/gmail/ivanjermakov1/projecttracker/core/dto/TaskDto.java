package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	public TaskDto() {
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
	
}
