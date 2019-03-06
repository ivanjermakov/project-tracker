package com.gmail.ivanjermakov1.projecttracker.core.dto;

public class AuthUserDto {
	
	public String login;
	public String password;
	
	public AuthUserDto() {
	}
	
	public AuthUserDto(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
}
