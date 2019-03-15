package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	@Query("select p from Project p join p.projectInfo i where p.user.id = :userId and i.name = :name")
	Project findByNameAndUser(@Param("name") String name, @Param("userId") Long userId);
	
	Optional<Project> findById(Long id);
	
	List<Project> findAllByUser(User user, Pageable pageable);
	
	@Query("select p from Project p join p.user u join p.projectInfo i " +
			"where u.userCredentials.login = :login and i.name = :name")
	Optional<Project> findByLoginAndName(@Param("login") String login, @Param("name") String name);
	
}
