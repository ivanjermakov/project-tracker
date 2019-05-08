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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ProjectTest {
	
	@Autowired
	private RegisterController registerController;
	
	@Autowired
	private AuthController authController;
	
	@Autowired
	private ProjectController projectController;
	
	private String token;
	private int projectsCount;
	
	@Before
	public void registerUserAndInitProjects() throws AuthenticationException, NoSuchEntityException, RegistrationException {
		RegisterUserDto registerUserDto = new RegisterUserDto("test", "password");
		registerController.register(registerUserDto);
		
		token = authController.authenticate(new AuthUserDto(registerUserDto.login, registerUserDto.password));
		projectsCount = 5;
		IntStream
				.range(0, projectsCount)
				.forEach(i -> {
					try {
						projectController.create(token, new NewProjectDto(true, "test_proj" + i, null, null));
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
	}
	
	@Test
	public void shouldGetAllUserProjects() throws NoSuchEntityException {
		List<ProjectDto> twoProjects = projectController.all(token, PageRequest.of(0, 2));
		List<ProjectDto> allProjects = projectController.all(token, PageRequest.of(0, Integer.MAX_VALUE));
		
		Assert.assertEquals(2, twoProjects.size());
		Assert.assertEquals(projectsCount, allProjects.size());
	}
	
}
