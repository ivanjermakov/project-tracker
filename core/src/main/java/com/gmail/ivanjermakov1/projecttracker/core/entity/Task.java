package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
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
	
	@ManyToOne
	@JoinColumn(name = "creator_id")
	private User creator;
	
	@Enumerated
	@Column(name = "type")
	private TaskType type;
	
	@Column(name = "estimate")
	private Double estimate;
	
	@Column(name = "elapsed")
	private Double elapsed;
	
	@Column(name = "opened")
	private LocalDateTime opened;
	
	@Column(name = "due")
	private LocalDate due;
	
	@OneToOne(mappedBy = "task", cascade = {CascadeType.ALL}, orphanRemoval = true)
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
	
	public Task(Project project, User creator, TaskType type, Double estimate, Double elapsed, LocalDateTime opened, LocalDate due, TaskInfo taskInfo, List<Task> subtasks) {
		this.project = project;
		this.creator = creator;
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
	
	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public TaskType getType() {
		return type;
	}
	
	public void setType(TaskType type) {
		this.type = type;
	}
	
	public Double getEstimate() {
		return estimate;
	}
	
	public void setEstimate(Double estimate) {
		this.estimate = estimate;
	}
	
	public Double getElapsed() {
		return elapsed;
	}
	
	public void setElapsed(Double elapsed) {
		this.elapsed = elapsed;
	}
	
	public LocalDateTime getOpened() {
		return opened;
	}
	
	public void setOpened(LocalDateTime opened) {
		this.opened = opened;
	}
	
	public LocalDate getDue() {
		return due;
	}
	
	public void setDue(LocalDate due) {
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
