package com.gmail.ivanjermakov1.projecttracker.core;

import com.gmail.ivanjermakov1.projecttracker.core.controller.AuthController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.ProjectController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.RegisterController;
import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.EditProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.RegistrationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
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
	private ProjectDto project;
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
	
	@Test
	public void shouldGetProject() throws NoSuchEntityException, AuthorizationException {
		project = projectController.get(
				token,
				authController.validate(token).login,
				projectController.all(token, PageRequest.of(0, 1))
						.stream()
						.findFirst().orElseThrow(NoSuchEntityException::new).name
		);
		
		Assert.assertNotNull(project);
	}
	
	@Test
	public void shouldEditProject() throws AuthorizationException, NoSuchEntityException {
		ProjectDto project = projectController.all(token, PageRequest.of(0, 1))
				.stream()
				.findFirst()
				.orElseThrow(NoSuchEntityException::new);
		
		Assert.assertNotNull(project);
		
		project = projectController.edit(token, new EditProjectDto(
				project.id,
				project.isPublic,
				project.name,
				project.description,
				project.about
		));
		
		Assert.assertNotNull(project);
		
		project = projectController.edit(token, new EditProjectDto(
				project.id,
				false,
				"test_proj_new_name",
				project.description,
				project.about
		));
		
		Assert.assertNotNull(project);
		Assert.assertEquals("test_proj_new_name", project.name);
	}
	
	//	TODO: fix test
	@Ignore
	@Test
	public void shouldDeleteProject() throws NoSuchEntityException, AuthorizationException {
		List<ProjectDto> allProjects = projectController.all(token, PageRequest.of(0, Integer.MAX_VALUE));
		
		projectController.delete(token, allProjects
				.stream()
				.findFirst()
				.orElseThrow(NoSuchEntityException::new).id);
		
		List<ProjectDto> allProjectsExceptDeleted = projectController.all(token, PageRequest.of(0, Integer.MAX_VALUE));
		
		Assert.assertEquals(allProjects.size() - 1, allProjectsExceptDeleted.size());
	}
	
}
