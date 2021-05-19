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
	
	@Query("select p from Project p join p.projectInfo i where p.user.id = :userId and i.name = :name")
	Optional<Project> findByNameAndUser(@Param("name") String name, @Param("userId") Long userId);
	
	Optional<Project> findById(Long id);

	@Query("select p from Project p, Role r where r.project = p and r.user.id = :userId")
	List<Project> findAllByUser(@Param("userId") Long userId, Pageable pageable);
	
	@Query("select p from Project p join p.user u join p.projectInfo i " +
			"where u.userCredentials.login = :login and i.name = :name")
	Optional<Project> findByLoginAndName(@Param("login") String login, @Param("name") String name);
	
	@Query("select p from Project p, Role r join p.user u join p.projectInfo i where r.project = p and r.user = :user and i.name like :search")
	List<Project> findContaining(@Param("user") User user, @Param("search") String search, Pageable pageable);
	
	default List<Project> find(User user, String search, Pageable pageable) {
		return findContaining(user, '%' + search + '%', pageable);
	}
	
}
