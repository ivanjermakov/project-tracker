package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "role")
	private UserRole role;
	
	public Role() {
	}
	
	public Role(Project project, User user, UserRole role) {
		this.project = project;
		this.user = user;
		this.role = role;
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
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserRole getRole() {
		return role;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}
	
}
