package com.gmail.ivanjermakov1.projecttracker.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_info")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "company")
	private String company;
	
	@Column(name = "location")
	private String location;
	
	public UserInfo() {
	}
	
	public UserInfo(String name, String bio, String url, String company, String location) {
		this.name = name;
		this.bio = bio;
		this.url = url;
		this.company = company;
		this.location = location;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getCompany() {
		return company;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
}
