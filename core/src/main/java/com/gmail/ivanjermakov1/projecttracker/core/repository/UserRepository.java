package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query("select u from User u inner join u.userCredentials c on c.login = :login")
	Optional<User> findUserByLogin(@Param("login") String login);
	
}
