package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskPriority;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@ManyToOne
	@JoinColumn(name = "project_id")
	Project project;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	Task parent;

	@ManyToOne
	@JoinColumn(name = "creator_id")
	User creator;

	@Enumerated
	@Column(name = "type")
	TaskType type;

	@Enumerated
	@Column(name = "status")
	TaskStatus status;

	@Enumerated
	@Column(name = "priority")
	TaskPriority priority;

	@Column(name = "tag")
	String tag;

	@Column(name = "estimate")
	Double estimate;

	@Transient
	Double elapsed;

	@Column(name = "opened")
	LocalDateTime opened;

	@Column(name = "due")
	LocalDate due;

	@OneToOne(mappedBy = "task", cascade = {CascadeType.ALL}, orphanRemoval = true)
	TaskInfo taskInfo;

	@OneToMany(mappedBy = "parent", cascade = {CascadeType.ALL}, orphanRemoval = true)
	List<Task> subtasks;

	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Activity> activities;

	@Transient
	Activity lastActivity;

	@ManyToOne
	@JoinColumn(name = "assignee_id")
	User assignee;

	@PostLoad
	public void postLoad() {
		elapsed = activities
				.stream()
				.mapToDouble(a -> a.getElapsed() != null ? a.getElapsed() : 0d)
				.sum();

		lastActivity = activities.stream()
				.filter(a -> Objects.nonNull(a.getTimestamp()))
				.max(Comparator.comparing(Activity::getTimestamp))
				.orElse(null);
	}

}
