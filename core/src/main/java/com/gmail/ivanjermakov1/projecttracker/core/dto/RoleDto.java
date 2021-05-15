package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;

public class RoleDto {

	private Long id;
	private ProjectDto project;
	private UserDto user;
	private UserRole role;

	public RoleDto() {
	}

	public RoleDto(Long id, ProjectDto project, UserDto user, UserRole role) {
		this.id = id;
		this.project = project;
		this.user = user;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProjectDto getProject() {
		return project;
	}

	public void setProject(ProjectDto project) {
		this.project = project;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
