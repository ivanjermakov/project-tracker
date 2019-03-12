package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Column(name = "type")
	private TaskType type;
	
	@Column(name = "estimate")
	private Duration estimate;
	
	@Column(name = "elapsed")
	private Duration elapsed;
	
	@Column(name = "opened")
	private LocalDateTime opened;
	
	@Column(name = "due")
	private LocalDateTime due;
	
	@OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "task_id", nullable = false)
	private TaskInfo taskInfo;
	
	@ManyToMany
	@JoinTable(
			name = "task_subtask",
			joinColumns = @JoinColumn(name = "task_id"),
			inverseJoinColumns = @JoinColumn(name = "subtask_id")
	)
	private List<Task> subtasks;
	
	public Task() {
	}
	
	public Task(Project project, TaskType type, Duration estimate, Duration elapsed, LocalDateTime opened, LocalDateTime due, TaskInfo taskInfo, List<Task> subtasks) {
		this.project = project;
		this.type = type;
		this.estimate = estimate;
		this.elapsed = elapsed;
		this.opened = opened;
		this.due = due;
		this.taskInfo = taskInfo;
		this.subtasks = subtasks;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public TaskType getType() {
		return type;
	}
	
	public void setType(TaskType type) {
		this.type = type;
	}
	
	public Duration getEstimate() {
		return estimate;
	}
	
	public void setEstimate(Duration estimate) {
		this.estimate = estimate;
	}
	
	public Duration getElapsed() {
		return elapsed;
	}
	
	public void setElapsed(Duration elapsed) {
		this.elapsed = elapsed;
	}
	
	public LocalDateTime getOpened() {
		return opened;
	}
	
	public void setOpened(LocalDateTime opened) {
		this.opened = opened;
	}
	
	public LocalDateTime getDue() {
		return due;
	}
	
	public void setDue(LocalDateTime due) {
		this.due = due;
	}
	
	public TaskInfo getTaskInfo() {
		return taskInfo;
	}
	
	public void setTaskInfo(TaskInfo taskInfo) {
		this.taskInfo = taskInfo;
	}
	
	public List<Task> getSubtasks() {
		return subtasks;
	}
	
	public void setSubtasks(List<Task> subtasks) {
		this.subtasks = subtasks;
	}
	
}
