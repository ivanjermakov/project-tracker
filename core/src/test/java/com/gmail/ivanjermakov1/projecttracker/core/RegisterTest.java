package com.gmail.ivanjermakov1.projecttracker.core;

import com.gmail.ivanjermakov1.projecttracker.core.controller.RegisterController;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.exception.RegistrationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@Transactional
public class RegisterTest {
	
	@Autowired
	private RegisterController registerController;
	
	@Test
	public void shouldRegisterUser() throws RegistrationException {
		registerController.register(new RegisterUserDto("test", "password"));
	}
	
}
