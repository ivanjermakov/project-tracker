package com.gmail.ivanjermakov1.projecttracker.core.dto;

public class RegisterUserDto {
	
	public String login;
	public String password;
	
	public RegisterUserDto() {
	}
	
	public RegisterUserDto(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
}
