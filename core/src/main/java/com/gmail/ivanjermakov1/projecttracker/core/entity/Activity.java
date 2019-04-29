package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "activity")
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "creator_id")
	private User creator;
	
	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private User assignee;
	
	@Enumerated
	@Column(name = "status")
	private TaskStatus status;
	
	@Column(name = "completion_difference")
	private Integer completion_difference;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	
	@Column(name = "description")
	private String description;
	
	public Activity() {
	}
	
	public Activity(Task task, User creator, User assignee, TaskStatus status, Integer completion_difference, LocalDateTime timestamp, String description) {
		this.task = task;
		this.creator = creator;
		this.assignee = assignee;
		this.status = status;
		this.completion_difference = completion_difference;
		this.timestamp = timestamp;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public User getAssignee() {
		return assignee;
	}
	
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	
	public TaskStatus getStatus() {
		return status;
	}
	
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public Integer getCompletion_difference() {
		return completion_difference;
	}
	
	public void setCompletion_difference(Integer completion_difference) {
		this.completion_difference = completion_difference;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
