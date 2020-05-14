package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.nontable.ProjectTaskType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

	@Query(value = "select * from find_all_tasks_by_project(:id)", nativeQuery = true)
	List<Task> findAllByProject(@Param("id") Long projectId, Pageable pageable);

	@Query(value = "select * from find_task_types_by_project(:id)", nativeQuery = true)
	List<ProjectTaskType> findTaskTypesByProject(@Param("id") Long projectId);

	@Query(value = "select * from find_all_tasks_by_creator(:id)", nativeQuery = true)
	List<Task> findAllByCreator(@Param("id") Long userId, Pageable pageable);

}
