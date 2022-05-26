package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"user"})
@ToString(exclude = {"user"})
@Entity
@Table(name = "user_info")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "user_id")
	User user;

	@Column(name = "name")
	String name;

	@Column(name = "bio")
	String bio;

	@Column(name = "url")
	String url;

	@Column(name = "company")
	String company;

	@Column(name = "location")
	String location;

	@Column(name = "skills")
	String skills;

    @Column(name = "avatarUrl")
    String avatarUrl;

}
