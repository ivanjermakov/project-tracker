package com.gmail.ivanjermakov1.projecttracker.core.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "public")
	private Boolean isPublic;
	
	@Column(name = "created")
	private LocalDateTime created;
	
	@OneToMany(mappedBy = "project")
	private List<Task> tasks;
	
	public Project() {
	}
	
	public Project(User user, Boolean isPublic, LocalDateTime created) {
		this.user = user;
		this.isPublic = isPublic;
		this.created = created;
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
	
	public void setPublic(Boolean aPublic) {
		isPublic = aPublic;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
}
