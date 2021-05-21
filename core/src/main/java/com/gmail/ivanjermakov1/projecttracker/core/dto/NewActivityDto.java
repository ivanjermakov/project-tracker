package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewActivityDto {

	@NotNull
	Long taskId;

	TaskStatus status;
	String description;

	@Min(value = 0, message = "elapsed time cannot be negative")
	Double elapsed;

	String assigneeLogin;

}
