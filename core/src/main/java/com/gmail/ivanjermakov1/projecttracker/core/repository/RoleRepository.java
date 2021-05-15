package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Role;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Optional<Role> findByUserAndProject(User user, Project project);

	List<UserRole> findAllByProject(Project project);
}
