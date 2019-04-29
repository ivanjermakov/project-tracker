package com.gmail.ivanjermakov1.projecttracker.core;

import com.gmail.ivanjermakov1.projecttracker.core.controller.AuthController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.RegisterController;
import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.UserDto;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.RegistrationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)

public class AuthTest {
	
	@Autowired
	private RegisterController registerController;
	
	@Autowired
	private AuthController authController;
	
	private RegisterUserDto registerUserDto;
	
	@Before
	public void registerUser() throws RegistrationException {
		registerUserDto = new RegisterUserDto("test", "password");
		
		registerController.register(registerUserDto);
	}
	
	@Test
	public void shouldAuthAndValidateUser() throws AuthenticationException, NoSuchEntityException, IOException {
		String token = authController.authenticate(new AuthUserDto(registerUserDto.login, registerUserDto.password));
		Assert.assertNotNull(token);
		
		UserDto user = authController.validate(token);
		Assert.assertNotNull(user);
		Assert.assertEquals(user.login, registerUserDto.login);
	}
	
}
