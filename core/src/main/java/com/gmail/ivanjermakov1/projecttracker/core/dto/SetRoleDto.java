package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;

public class SetRoleDto {
	String login;
	Long projectId;
	UserRole role;

	public SetRoleDto() {
	}

	public SetRoleDto(String login, Long projectId, UserRole role) {
		this.login = login;
		this.projectId = projectId;
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
}
