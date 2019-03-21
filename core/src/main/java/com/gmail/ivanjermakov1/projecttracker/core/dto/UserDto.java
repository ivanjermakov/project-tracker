package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class UserDto {
	
	public Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	public LocalDate joined;
	public UserInfoDto userInfo;
	public UserCredentialsDto userCredentials;
	
	public UserDto() {
	}
	
	public UserDto(Long id, LocalDate joined, UserInfoDto userInfo, UserCredentialsDto userCredentials) {
		this.id = id;
		this.joined = joined;
		this.userInfo = userInfo;
		this.userCredentials = userCredentials;
	}
	
}
