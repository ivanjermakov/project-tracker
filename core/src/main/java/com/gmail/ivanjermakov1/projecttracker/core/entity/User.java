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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "user", schema = "public")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "joined")
	private LocalDate joined;
	
	@OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private UserCredentials userCredentials;
	
	@OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private UserInfo userInfo;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "user_follower",
			joinColumns = @JoinColumn(name = "follower_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<User> followers;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "user_follower",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "follower_id")
	)
	private Set<User> following;
	
	public User() {
	}
	
	public User(LocalDate joined) {
		this.joined = joined;
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
	
	public Set<User> getFollowers() {
		return followers;
	}
	
	public void setFollowers(Set<User> followers) {
		this.followers = followers;
	}
	
	public Set<User> getFollowing() {
		return following;
	}
	
	public void setFollowing(Set<User> followed) {
		this.following = followed;
	}
	
}
