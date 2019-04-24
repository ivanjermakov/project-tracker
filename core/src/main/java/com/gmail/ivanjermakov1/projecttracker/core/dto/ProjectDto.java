package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ProjectDto {
	
	public Long id;
	public Boolean isPublic;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime created;
	public String name;
	public String description;
	public String about;
	public UserDto user;
	
	public ProjectDto() {
	}
	
	public ProjectDto(Long id, Boolean isPublic, LocalDateTime created, String name, String description, String about, UserDto user) {
		this.id = id;
		this.isPublic = isPublic;
		this.created = created;
		this.name = name;
		this.description = description;
		this.about = about;
		this.user = user;
	}
	
}
