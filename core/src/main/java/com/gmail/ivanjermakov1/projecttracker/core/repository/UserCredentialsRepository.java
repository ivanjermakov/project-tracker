package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Long> {
	
	Optional<UserCredentials> findByLogin(String login);
	
}
