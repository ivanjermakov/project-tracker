package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;

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
	
	public Task() {
	}
	
	public Task(Project project, TaskType type, Duration estimate, Duration elapsed, LocalDateTime opened, LocalDateTime due, TaskInfo taskInfo) {
		this.project = project;
		this.type = type;
		this.estimate = estimate;
		this.elapsed = elapsed;
		this.opened = opened;
		this.due = due;
		this.taskInfo = taskInfo;
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
	
}
