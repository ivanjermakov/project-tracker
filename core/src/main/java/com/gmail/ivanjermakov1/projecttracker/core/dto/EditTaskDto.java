package com.gmail.ivanjermakov1.projecttracker.core.dto;

import javax.validation.constraints.NotNull;

public class EditTaskDto {
	
	@NotNull
	public Long id;
	
	@NotNull
	public String name;
	
	public String description;
	
	public EditTaskDto() {
	}
	
	public EditTaskDto(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
}
