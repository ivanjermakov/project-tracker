package com.gmail.ivanjermakov1.projecttracker.core.entity;

import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

//TODO: priority
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "activity")
public class Activity {

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

	@ManyToOne
	@JoinColumn(name = "assignee_id")
	User assignee;

	@Enumerated
	@Column(name = "status")
	TaskStatus status;

    @Enumerated
    @Column(name = "type")
    TaskType type;

	@Column(name = "elapsed")
	Double elapsed;

	@Column(name = "timestamp")
	LocalDateTime timestamp;

	@Column(name = "description")
	String description;

}
