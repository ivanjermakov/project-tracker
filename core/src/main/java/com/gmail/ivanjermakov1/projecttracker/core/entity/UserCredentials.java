package com.gmail.ivanjermakov1.projecttracker.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_credentials")
public class UserCredentials {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "hash")
	private String hash;
	
	public UserCredentials() {
	}
	
	public UserCredentials(String login, String hash) {
		this.login = login;
		this.hash = hash;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
	
}
