package com.gmail.ivanjermakov1.projecttracker.core.dto;

import javax.validation.constraints.NotNull;

public class EditProjectDto {
	
	@NotNull
	public Long id;
	
	@NotNull
	public Boolean isPublic;
	
	@NotNull
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
