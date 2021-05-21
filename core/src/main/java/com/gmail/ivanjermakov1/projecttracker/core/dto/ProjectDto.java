package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectDto {

	Long id;
	Boolean is;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime created;
	String name;
	String description;
	String about;
	UserDto user;
	Double estimate;
	Double elapsed;

}
