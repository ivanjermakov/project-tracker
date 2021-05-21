package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskPriority;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {

	Long id;
	ProjectDto project;
	UserDto creator;
	TaskType type;
	TaskStatus status;
	TaskPriority priority;
	String tag;
	Double estimate;
	Double elapsed;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime opened;

	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate started;

	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate due;

	String name;
	String description;
	List<TaskDto> subtasks;
	Long parentTaskId;

	ActivityDto lastActivity;

	UserDto assignee;

	public TaskDto compute() {
		subtasks.forEach(TaskDto::compute);

		elapsed = elapsed != null ? elapsed : 0d;
		elapsed += subtasks
				.stream()
				.filter(s -> Objects.nonNull(s.elapsed))
				.mapToDouble(s -> s.elapsed)
				.sum();
		elapsed = elapsed != 0 ? elapsed : null;

		estimate = estimate != null ? estimate : 0d;
		estimate += subtasks
				.stream()
				.filter(s -> Objects.nonNull(s.estimate))
				.mapToDouble(s -> s.estimate)
				.sum();
		estimate = estimate != 0 ? estimate : null;

		return this;
	}

}
