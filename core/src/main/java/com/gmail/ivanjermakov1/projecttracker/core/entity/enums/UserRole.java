package com.gmail.ivanjermakov1.projecttracker.core.entity.enums;

/**
 * Number in constructor parameter represents level of access
 */
public enum UserRole {
	OWNER(10),
	MODERATOR(7),
	MEMBER(5),
	UNAUTHORIZED(0);
	
	public Integer level;
	
	UserRole(Integer level) {
		this.level = level;
	}
}
