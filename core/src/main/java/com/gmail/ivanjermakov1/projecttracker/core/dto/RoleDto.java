package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {

	Long id;
	ProjectDto project;
	UserDto user;
	UserRole role;

}
