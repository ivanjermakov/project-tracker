package com.gmail.ivanjermakov1.projecttracker.core.dto.stats;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;

public class ProjectTaskTypeDto {
	
	public TaskType type;
	public Integer count;
	
	public ProjectTaskTypeDto() {
	}
	
	public ProjectTaskTypeDto(TaskType type, Integer count) {
		this.type = type;
		this.count = count;
	}
	
}
