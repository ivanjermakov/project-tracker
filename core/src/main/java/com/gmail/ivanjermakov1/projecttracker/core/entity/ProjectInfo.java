package com.gmail.ivanjermakov1.projecttracker.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "project_info")
public class ProjectInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "about")
	private String about;
	
	public ProjectInfo() {
	}
	
	public ProjectInfo(Project project, String name, String description, String about) {
		this.project = project;
		this.name = name;
		this.description = description;
		this.about = about;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAbout() {
		return about;
	}
	
	public void setAbout(String about) {
		this.about = about;
	}
	
}
