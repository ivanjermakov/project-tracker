package com.gmail.ivanjermakov1.projecttracker.core.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@Column(name = "public")
	Boolean isPublic;

	@Column(name = "created")
	LocalDateTime created;

	@OneToOne(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	ProjectInfo projectInfo;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Role> roles;

	@OneToMany(mappedBy = "project")
	List<Task> tasks;

	@Transient
	Double estimate;

	@Transient
	Double elapsed;
	
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

}
