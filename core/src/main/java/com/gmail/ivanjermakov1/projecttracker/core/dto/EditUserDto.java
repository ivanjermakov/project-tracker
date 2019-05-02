package com.gmail.ivanjermakov1.projecttracker.core.dto;

import javax.validation.constraints.NotNull;

public class EditUserDto {
	
	@NotNull
	public Long id;
	
	@NotNull
	public String login;
	
	public String name;
	public String bio;
	public String url;
	public String company;
	public String location;
	
	public EditUserDto() {
	}
	
	public EditUserDto(Long id, String login, String name, String bio, String url, String company, String location) {
		this.id = id;
		this.login = login;
		this.name = name;
		this.bio = bio;
		this.url = url;
		this.company = company;
		this.location = location;
	}
	
}
