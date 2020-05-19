package com.gmail.ivanjermakov1.projecttracker.core;

import com.gmail.ivanjermakov1.projecttracker.core.controller.AuthController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.ProjectController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.RegisterController;
import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.RegistrationException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class PerformanceTest {

	@Autowired
	private RegisterController registerController;
	@Autowired
	private AuthController authController;
	@Autowired
	private ProjectController projectController;

	@Test
	@Ignore
	public void users() {
		IntStream.range(0, 1000).forEach(i -> {
			try {
				registerController.register(new RegisterUserDto(UUID.randomUUID().toString(), "password"));
			} catch (RegistrationException e) {
				e.printStackTrace();
			}
		});
	}

	@Test
	@Ignore
	public void projects() throws RegistrationException, AuthenticationException, NoSuchEntityException {
		RegisterUserDto registerUserDto = new RegisterUserDto("test", "password");
		registerController.register(registerUserDto);

		String token = authController.authenticate(new AuthUserDto(registerUserDto.login, registerUserDto.password));
		IntStream
				.range(0, 1000)
				.forEach(i -> {
					try {
						projectController.create(token, new NewProjectDto(true, "test_proj" + i, null, null));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

		List<ProjectDto> projects = projectController.all(token, Pageable.unpaged());
		Assert.assertEquals(1000, projects.size());
	}


}
