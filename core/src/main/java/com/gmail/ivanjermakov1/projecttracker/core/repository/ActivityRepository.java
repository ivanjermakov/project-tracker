package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Activity;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.nontable.ProjectActivity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Long> {

	@Query(value = "select * from find_all_by_task(:id)", nativeQuery = true)
	List<Activity> findAllByTask(@Param("id") Long taskId, Pageable pageable);

	@Query(value = "select * from find_all_by_project(:id)", nativeQuery = true)
	List<Activity> findAllByProject(@Param("id") Long projectId, Pageable pageable);

	@Query("select a from Activity a join a.task t join t.project p join p.user u where u = :user")
	List<Activity> findAllByUser(@Param("user") User user, Pageable pageable);

	@Query(value = "select date(a.timestamp) as day, count(*) as activityAmount\n" +
			"from project p\n" +
			"         join task t on p.id = t.project_id\n" +
			"         join activity a on t.id = a.task_id\n" +
			"where p.id = :id\n" +
			"group by day\n" +
			"order by day", nativeQuery = true)
	List<ProjectActivity> findActivitiesByProject(@Param("id") Long projectId);

	@Query(value = "select *\n" +
			"from task t\n" +
			"         join (select *\n" +
			"               from activity\n" +
			"               where assignee_id = :id\n" +
			"               order by timestamp desc\n" +
			"               limit 1) as aa\n" +
			"              on t.id = aa.task_id", nativeQuery = true)
	List<Activity> findAllAssigneeActivities(@Param("id") Long assigneeId, Pageable pageable);

}
