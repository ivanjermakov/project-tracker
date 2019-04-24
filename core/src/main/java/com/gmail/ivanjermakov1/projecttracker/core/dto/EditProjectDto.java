package com.gmail.ivanjermakov1.projecttracker.core.dto;

public class EditProjectDto {
	
	public Long id;
	public Boolean isPublic;
	public String name;
	public String description;
	public String about;
	
	public EditProjectDto() {
	}
	
	public EditProjectDto(Long id, Boolean isPublic, String name, String description, String about) {
		this.id = id;
		this.isPublic = isPublic;
		this.name = name;
		this.description = description;
		this.about = about;
	}
	
}
