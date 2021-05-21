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
public class CommentDto {

	Long id;
	TaskDto task;
	UserDto creator;
	String text;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime timestamp;

}
