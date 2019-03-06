package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Token;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.UserCredentials;
import com.gmail.ivanjermakov1.projecttracker.core.entity.UserInfo;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.RegistrationException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.TokenRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.UserRepository;
import com.gmail.ivanjermakov1.projecttracker.core.security.Hasher;
import com.gmail.ivanjermakov1.projecttracker.core.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class UserService {
	
	private final TokenRepository tokenRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(TokenRepository tokenRepository, UserRepository userRepository) {
		this.tokenRepository = tokenRepository;
		this.userRepository = userRepository;
	}
	
	public String authenticate(AuthUserDto authUserDto) throws NoSuchEntityException, AuthenticationException {
		User user = userRepository.findUserByLogin(authUserDto.login)
				.orElseThrow(() -> new NoSuchEntityException("no user with such login"));
		if (!Hasher.check(authUserDto.password, user.getUserCredentials().getHash()))
			throw new AuthenticationException("invalid credentials");
		
		Token token = new Token(user, TokenGenerator.generate());
		return tokenRepository.save(token).getToken();
	}
	
	public User validate(String tokenValue) throws NoSuchEntityException {
		return tokenRepository.findByToken(tokenValue)
				.orElseThrow(() -> new NoSuchEntityException("no such token"))
				.getUser();
	}
	
	public void register(RegisterUserDto registerUserDto) throws RegistrationException {
		if (userRepository.findUserByLogin(registerUserDto.login).isPresent())
			throw new RegistrationException("user with such login already exist");
		
		User user = new User(
				LocalDate.now(),
				new UserCredentials(registerUserDto.login, Hasher.getHash(registerUserDto.password)),
				new UserInfo()
		);
		
		userRepository.save(user);
	}
	
}
