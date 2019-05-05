package com.gmail.ivanjermakov1.projecttracker.core.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthUserDto {
	
	@NotBlank
	public String login;
	
	@NotBlank
	public String password;
	
	public AuthUserDto() {
	}
	
	public AuthUserDto(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
}
