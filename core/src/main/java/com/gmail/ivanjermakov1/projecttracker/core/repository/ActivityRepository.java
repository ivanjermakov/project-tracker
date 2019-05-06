package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Activity;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
	
	List<Activity> findAllByTask(Task task, Pageable pageable);
	
	@Query("select a from Activity a join a.task t join t.project p where p = :project")
	List<Activity> findAllByProject(@Param("project") Project project, Pageable pageable);
	
	@Query("select a from Activity a join a.task t join t.project p join p.user u where u = :user")
	List<Activity> findAllByUser(@Param("user") User user, Pageable pageable);
	
}
