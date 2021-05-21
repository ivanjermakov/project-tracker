package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@ManyToOne
	@JoinColumn(name = "task_id")
	Task task;

	@ManyToOne
	@JoinColumn(name = "creator_id")
	User creator;

	@Column(name = "text")
	String text;

	@Column(name = "timestamp")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime timestamp;

}
