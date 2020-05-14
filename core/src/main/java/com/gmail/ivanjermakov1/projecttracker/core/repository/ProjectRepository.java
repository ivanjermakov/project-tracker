package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Long> {


	@Query(value = "select * from find_project_by_name_and_user(:name, :id)", nativeQuery = true)
	Optional<Project> findByNameAndUser(@Param("name") String name, @Param("id") Long userId);

	@Query(value = "select * from find_all_projects_by_user(:id)", nativeQuery = true)
	List<Project> findAllByUser(@Param("id") Long userId, Pageable pageable);

	@Query(value = "select * from find_projects_by_login_and_name(:login, :name)", nativeQuery = true)
	Optional<Project> findByLoginAndName(@Param("login") String login, @Param("name") String name);

	@Query(value = "select * from find_projects_containing(:id, :search)", nativeQuery = true)
	List<Project> findContaining(@Param("id") Long userId, @Param("search") String search, Pageable pageable);

	default List<Project> find(User user, String search, Pageable pageable) {
		return findContaining(user.getId(), '%' + search + '%', pageable);
	}

}
