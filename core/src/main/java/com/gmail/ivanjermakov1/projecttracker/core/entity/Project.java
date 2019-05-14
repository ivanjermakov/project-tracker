package com.gmail.ivanjermakov1.projecttracker.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "public")
	private Boolean isPublic;
	
	@Column(name = "created")
	private LocalDateTime created;
	
	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private ProjectInfo projectInfo;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Role> roles;
	
	@OneToMany(mappedBy = "project")
	private List<Task> tasks;
	
	@Transient
	private Double estimate;
	
	@Transient
	private Double elapsed;
	
	public Project() {
	}
	
	public Project(User user, Boolean isPublic, LocalDateTime created) {
		this.user = user;
		this.isPublic = isPublic;
		this.created = created;
	}
	
	@PostLoad
	public void postLoad() {
		estimate = tasks
				.stream()
				.mapToDouble(t -> t.getEstimate() != null ? t.getEstimate() : 0)
				.sum();
		
		elapsed = tasks
				.stream()
				.mapToDouble(t -> t.getElapsed() != null ? t.getElapsed() : 0)
				.sum();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Boolean getPublic() {
		return isPublic;
	}
	
	public void setPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
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
	
}
