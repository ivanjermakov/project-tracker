package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Token;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.UserCredentials;
import com.gmail.ivanjermakov1.projecttracker.core.entity.UserInfo;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.RegistrationException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.TokenRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.UserCredentialsRepository;
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
	private final UserCredentialsRepository userCredentialsRepository;
	
	@Autowired
	public UserService(TokenRepository tokenRepository, UserRepository userRepository, UserCredentialsRepository userCredentialsRepository) {
		this.tokenRepository = tokenRepository;
		this.userRepository = userRepository;
		this.userCredentialsRepository = userCredentialsRepository;
	}
	
	public String authenticate(AuthUserDto authUserDto) throws NoSuchEntityException, AuthenticationException {
		User user = userCredentialsRepository.findByLogin(authUserDto.login)
				.orElseThrow(() -> new NoSuchEntityException("no user with such login")).getUser();
		if (!Hasher.check(authUserDto.password, user.getUserCredentials().getHash()))
			throw new AuthenticationException("invalid userCredentials");
		
		Token token = new Token(user, TokenGenerator.generate());
		return tokenRepository.save(token).getToken();
	}
	
	public User validate(String tokenValue) throws NoSuchEntityException {
		return tokenRepository.findByToken(tokenValue)
				.orElseThrow(() -> new NoSuchEntityException("no such token"))
				.getUser();
	}
	
	public void register(RegisterUserDto registerUserDto) throws RegistrationException {
		if (userCredentialsRepository.findByLogin(registerUserDto.login).isPresent())
			throw new RegistrationException("user with such login already exist");
		
		User user = new User(LocalDate.now());
		userRepository.save(user);
		
		user.setUserCredentials(new UserCredentials(user, registerUserDto.login, Hasher.getHash(registerUserDto.password)));
		user.setUserInfo(new UserInfo(user));
		userRepository.save(user);
	}
	
	public User getUser(User user, String login) throws NoSuchEntityException {
//		TODO: maybe some kind of authorization
		
		return userCredentialsRepository.findByLogin(login)
				.orElseThrow(() -> new NoSuchEntityException("no such user by given login"))
				.getUser();
	}
	
}
