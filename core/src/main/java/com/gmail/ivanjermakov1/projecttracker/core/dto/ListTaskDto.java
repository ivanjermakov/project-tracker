package com.gmail.ivanjermakov1.projecttracker.core.dto;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskPriority;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.function.Predicate;

import static com.gmail.ivanjermakov1.projecttracker.core.util.Strings.nullOrEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListTaskDto implements Predicate<Task> {

	TaskStatus status;
	TaskType type;
	TaskPriority priority;
	String assignee;
	String name;
	//TODO
	String tag;

	@Override
	public boolean test(Task task) {
		if (status != null && task.getStatus() != status) {
			return false;
		}
		if (type != null && task.getType() != type) {
			return false;
		}
		if (priority != null && task.getPriority() != priority) {
			return false;
		}
		if (!nullOrEmpty(assignee)) {
			if (task.getAssignee() == null) {
				return false;
			}
			if (task.getAssignee() != null && !task.getAssignee().getUserCredentials().getLogin().equals(assignee)) {
				return false;
			}
		}
		if (!nullOrEmpty(name) && !task.getTaskInfo().getName().contains(name)) {
			return false;
		}
		if (!nullOrEmpty(tag) && !task.getTag().contains(tag)) {
			return false;
		}
		return true;
	}

}
