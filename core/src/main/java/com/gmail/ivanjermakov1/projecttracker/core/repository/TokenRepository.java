package com.gmail.ivanjermakov1.projecttracker.core.repository;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {

	@Query(value = "select * from find_token_by_token(:token)", nativeQuery = true)
	Optional<Token> findByToken(@Param("token") String token);

}
