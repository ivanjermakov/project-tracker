package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ProjectDto {
	
	public Long id;
	public Boolean isPublic;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime created;
	public ProjectInfoDto projectInfo;
	
	public ProjectDto() {
	}
	
	public ProjectDto(Long id, Boolean isPublic, LocalDateTime created, ProjectInfoDto projectInfo) {
		this.id = id;
		this.isPublic = isPublic;
		this.created = created;
		this.projectInfo = projectInfo;
	}
	
}
