package com.gmail.ivanjermakov1.projecttracker.core;

import com.gmail.ivanjermakov1.projecttracker.core.controller.RegisterController;
import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.RegistrationException;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class RegisterTest {
	
	@Autowired
	private RegisterController registerController;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void shouldRegisterUser() throws RegistrationException, AuthenticationException, NoSuchEntityException {
		RegisterUserDto registerUserDto = new RegisterUserDto("login", "password");
		
		registerController.register(registerUserDto);
		
		Assert.assertNotNull(userService.authenticate(new AuthUserDto("login", "password")));
	}
	
}
