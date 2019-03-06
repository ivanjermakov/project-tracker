package com.gmail.ivanjermakov1.projecttracker.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "joined")
	private LocalDate joined;
	
	@OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "user_id", nullable = false)
	private UserCredentials userCredentials;
	
	@OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
//	name = "user_id_" is temporarily fix of hibernate bug
//	TODO: investigate
	@JoinColumn(name = "user_id_", nullable = false)
	private UserInfo userInfo;
	
	@ManyToMany
	@JoinTable(
			name = "user_follower",
			joinColumns = @JoinColumn(name = "follower_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> followers;
	
	@ManyToMany
	@JoinTable(
			name = "user_follower",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "follower_id")
	)
	private List<User> followed;
	
	@OneToMany(mappedBy = "user")
	List<Token> tokens;
	
	public User() {
	}
	
	public User(LocalDate joined, UserCredentials userCredentials, UserInfo userInfo) {
		this.joined = joined;
		this.userCredentials = userCredentials;
		this.userInfo = userInfo;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getJoined() {
		return joined;
	}
	
	public void setJoined(LocalDate joined) {
		this.joined = joined;
	}
	
	public UserCredentials getUserCredentials() {
		return userCredentials;
	}
	
	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public List<User> getFollowers() {
		return followers;
	}
	
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	
	public List<User> getFollowed() {
		return followed;
	}
	
	public void setFollowed(List<User> followed) {
		this.followed = followed;
	}
	
}
