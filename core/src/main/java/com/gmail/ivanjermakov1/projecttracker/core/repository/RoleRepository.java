package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

	@Query(value = "select * from find_role_by_user_and_project(:userId, :projectId)", nativeQuery = true)
	Optional<Role> findByUserAndProject(@Param("userId") Long userId, @Param("projectId") Long projectId);

}
