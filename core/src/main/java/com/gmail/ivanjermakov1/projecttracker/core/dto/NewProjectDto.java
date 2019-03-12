package com.gmail.ivanjermakov1.projecttracker.core.dto;

public class NewProjectDto {
	
	public Boolean isPublic;
	public String name;
	public String description;
	public String about;
	
	public NewProjectDto() {
	}
	
	public NewProjectDto(Boolean isPublic, String name, String description, String about) {
		this.isPublic = isPublic;
		this.name = name;
		this.description = description;
		this.about = about;
	}
	
}
