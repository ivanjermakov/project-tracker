package com.gmail.ivanjermakov1.projecttracker.core.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EditProjectDto {
	
	@NotNull
	public Long id;
	
	@NotNull
	public Boolean isPublic;
	
	@NotBlank
	@Pattern(regexp = "^[\\w-]{3,}[0-9a-zA-Z]$", message = "should match the pattern /^[\\w-]{3,30}[0-9a-zA-Z]$/")
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
