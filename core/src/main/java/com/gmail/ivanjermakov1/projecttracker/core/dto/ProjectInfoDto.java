package com.gmail.ivanjermakov1.projecttracker.core.dto;

public class ProjectInfoDto {
	
	public String name;
	public String description;
	public String about;
	
	public ProjectInfoDto() {
	}
	
	public ProjectInfoDto(String name, String description, String about) {
		this.name = name;
		this.description = description;
		this.about = about;
	}
	
}
