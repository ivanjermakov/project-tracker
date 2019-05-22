package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.nontable.ProjectTaskType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
	
	List<Task> findAllByProject(Project project, Pageable pageable);
	
	@Query(value = "select t.type, count(*)\n" +
			"from project p\n" +
			"         join task t on p.id = t.project_id\n" +
			"where p.id = :id\n" +
			"group by t.type\n" +
			"order by t.type", nativeQuery = true)
	List<ProjectTaskType> findTaskTypesByProject(@Param("id") Long projectId);
	
	List<Task> findAllByCreator(User user, Pageable pageable);
	
}
