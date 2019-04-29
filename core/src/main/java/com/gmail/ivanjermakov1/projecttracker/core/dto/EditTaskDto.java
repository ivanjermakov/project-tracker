package com.gmail.ivanjermakov1.projecttracker.core.dto;

public class EditTaskDto {
	
	public Long id;
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
