package com.gmail.ivanjermakov1.projecttracker.core.dto.stats;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectTaskTypeDto {

	TaskType type;
	Integer count;

}
