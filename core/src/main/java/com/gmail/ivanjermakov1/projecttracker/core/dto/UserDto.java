package com.gmail.ivanjermakov1.projecttracker.core.dto;

import java.time.LocalDate;

public class UserDto {
	
	public Long id;
	public LocalDate joined;
	public UserInfoDto userInfo;
	
	public UserDto() {
	}
	
	public UserDto(Long id, LocalDate joined, UserInfoDto userInfo) {
		this.id = id;
		this.joined = joined;
		this.userInfo = userInfo;
	}
	
}
