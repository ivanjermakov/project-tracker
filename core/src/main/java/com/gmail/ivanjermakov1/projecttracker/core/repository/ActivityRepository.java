package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Activity;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
	
	List<Activity> findAllByTask(Task task, Pageable pageable);
	
}
