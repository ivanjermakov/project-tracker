package com.gmail.ivanjermakov1.projecttracker.core.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegisterUserDto {
	
	@NotBlank
	@Pattern(regexp = "^[\\w-]{3,}[0-9a-zA-Z]$", message = "should match the pattern /^[\\w-]{3,30}[0-9a-zA-Z]$/")
	public String login;
	
	@NotBlank
	@Pattern(regexp = "^(.){8,}$", message = "should match the pattern /^(.){8,}$/")
	public String password;
	
	public RegisterUserDto() {
	}
	
	public RegisterUserDto(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
}
