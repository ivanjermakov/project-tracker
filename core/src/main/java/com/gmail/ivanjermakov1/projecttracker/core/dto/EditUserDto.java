package com.gmail.ivanjermakov1.projecttracker.core.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EditUserDto {

	@NotNull
	Long id;

	@NotBlank
	String login;

	String name;
	String bio;
	String url;
	String company;
	String location;
	String skills;

}
