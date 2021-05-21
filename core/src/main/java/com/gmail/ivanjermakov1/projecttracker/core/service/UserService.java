package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.EditUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Token;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.UserCredentials;
import com.gmail.ivanjermakov1.projecttracker.core.entity.UserInfo;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.RegistrationException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.TokenRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.UserCredentialsRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.UserRepository;
import com.gmail.ivanjermakov1.projecttracker.core.security.Hasher;
import com.gmail.ivanjermakov1.projecttracker.core.security.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
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
		User user = userCredentialsRepository.findByLogin(authUserDto.getLogin())
				.orElseThrow(() -> new NoSuchEntityException("no user with such login")).getUser();
		if (!Hasher.check(authUserDto.getPassword(), user.getUserCredentials().getHash()))
			throw new AuthenticationException("invalid userCredentials");

		Token token = new Token(null, user, TokenGenerator.generate());
		return tokenRepository.save(token).getToken();
	}

	public User validate(String tokenValue) throws NoSuchEntityException {
		return tokenRepository.findByToken(tokenValue)
				.orElseThrow(() -> new NoSuchEntityException("no such token"))
				.getUser();
	}

	public void register(RegisterUserDto registerUserDto) throws RegistrationException {
		if (userCredentialsRepository.findByLogin(registerUserDto.getLogin()).isPresent())
			throw new RegistrationException("user with such login already exist");

		User user = new User(null, LocalDate.now(), null, null, null, null);
		userRepository.save(user);

		user.setUserCredentials(new UserCredentials(null, user, registerUserDto.getLogin(), Hasher.getHash(registerUserDto.getPassword())));
		user.setUserInfo(new UserInfo(null, user, null, null, null, null, null));
		userRepository.save(user);
	}

	public User getUser(User user, String login) throws NoSuchEntityException {
		return userCredentialsRepository.findByLogin(login)
				.orElseThrow(() -> new NoSuchEntityException("no such user by given login"))
				.getUser();
	}

	public User edit(User user, EditUserDto editUserDto) throws NoSuchEntityException, AuthorizationException {
		User editingUser = userRepository.findById(editUserDto.getId()).orElseThrow(() -> new NoSuchEntityException("no user with such id"));
		if (!user.getId().equals(editingUser.getId())) throw new AuthorizationException("no permission");

		editingUser.getUserCredentials().setLogin(editUserDto.getLogin());

		UserInfo userInfo = editingUser.getUserInfo();
		userInfo.setName(editUserDto.getName());
		userInfo.setBio(editUserDto.getBio());
		userInfo.setCompany(editUserDto.getCompany());
		userInfo.setUrl(editUserDto.getUrl());
		userInfo.setLocation(editUserDto.getLocation());
		editingUser.setUserInfo(userInfo);

		return userRepository.save(editingUser);
	}

	public void follow(User user, User followUser, Boolean follow) {
		boolean currentStatus = user.getFollowing().contains(followUser);
		if (follow == currentStatus) return;
		if (follow) {
			user.getFollowing().add(followUser);
		} else {
			user.getFollowing().remove(followUser);
		}
		userRepository.save(user);
	}

}
