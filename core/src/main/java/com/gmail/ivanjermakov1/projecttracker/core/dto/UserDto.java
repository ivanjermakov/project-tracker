package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class UserDto {
	
	public Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate joined;
	public UserInfoDto userInfo;
	public String login;
	
	public UserDto() {
	}
	
	public UserDto(Long id, LocalDate joined, UserInfoDto userInfo, String login) {
		this.id = id;
		this.joined = joined;
		this.userInfo = userInfo;
		this.login = login;
	}
	
}
