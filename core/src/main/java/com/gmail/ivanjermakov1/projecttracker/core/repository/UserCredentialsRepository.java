package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.UserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends CrudRepository<UserCredentials, Long> {
	
	UserCredentials findByLogin(String login);
	
}
