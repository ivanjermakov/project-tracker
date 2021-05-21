package com.gmail.ivanjermakov1.projecttracker.core.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EditProjectDto {

	@NotNull
	Long id;

	@NotNull
	Boolean isPublic;

	@NotBlank
	@Pattern(regexp = "^[\\w-]{3,}[0-9a-zA-Z]$", message = "should match the pattern /^[\\w-]{3,30}[0-9a-zA-Z]$/")
	String name;

	String description;
	String about;

}
