package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Activity;
import com.gmail.ivanjermakov1.projecttracker.core.entity.nontable.ProjectActivity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {

	@Query(value = "select * from find_all_activities_by_task(:id)", nativeQuery = true)
	List<Activity> findAllByTask(@Param("id") Long taskId, Pageable pageable);

	@Query(value = "select * from find_all_activities_by_project(:id)", nativeQuery = true)
	List<Activity> findAllByProject(@Param("id") Long projectId, Pageable pageable);

	@Query(value = "select * from find_all_activities_by_user(:id)", nativeQuery = true)
	List<Activity> findAllByUser(@Param("id") Long userId, Pageable pageable);

	@Query(value = "select * from find_all_activities_by_project(:id)", nativeQuery = true)
	List<ProjectActivity> findActivitiesByProject(@Param("id") Long projectId);

	@Query(value = "select * from find_all_assignee_activities(:id)", nativeQuery = true)
	List<Activity> findAllAssigneeActivities(@Param("id") Long assigneeId, Pageable pageable);

}
