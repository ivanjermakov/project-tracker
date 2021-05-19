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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getJoined() {
		return joined;
	}

	public void setJoined(LocalDate joined) {
		this.joined = joined;
	}

	public UserInfoDto getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoDto userInfo) {
		this.userInfo = userInfo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
