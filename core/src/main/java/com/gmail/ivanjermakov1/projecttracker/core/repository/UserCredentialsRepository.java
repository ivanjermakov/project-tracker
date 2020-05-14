package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.UserCredentials;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Long> {

	@Query(value = "select * from find_user_credentials_by_login(:login)", nativeQuery = true)
	Optional<UserCredentials> findByLogin(@Param("login") String login);

}
