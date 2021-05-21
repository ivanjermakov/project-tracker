package com.gmail.ivanjermakov1.projecttracker.core.dto.stats;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectActivityDto {

	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate day;

	Integer activityAmount;

}
