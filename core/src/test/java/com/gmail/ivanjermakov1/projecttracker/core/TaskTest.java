package com.gmail.ivanjermakov1.projecttracker.core;

import com.gmail.ivanjermakov1.projecttracker.core.controller.AuthController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.ProjectController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.RegisterController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.TaskController;
import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.TaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskPriority;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.DuplicationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.RegistrationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TaskTest {
	
	@Autowired
	private RegisterController registerController;
	
	@Autowired
	private AuthController authController;
	
	@Autowired
	private ProjectController projectController;
	
	@Autowired
	private TaskController taskController;
	
	private String token;
	private List<TaskDto> tasks;
	private ProjectDto project;
	
	@Before
	public void registerUserAndInitProjects() throws AuthenticationException, NoSuchEntityException, RegistrationException, DuplicationException {
		RegisterUserDto registerUserDto = new RegisterUserDto("test", "password");
		registerController.register(registerUserDto);
		
		token = authController.authenticate(new AuthUserDto(registerUserDto.getLogin(), registerUserDto.getPassword()));
		int tasksCount = 5;
		
		project = projectController.create(token, new NewProjectDto(true, "test_proj", null, null));
		
		tasks = IntStream
				.range(0, tasksCount)
				.boxed()
				.map(i -> {
					try {
						return taskController.create(token, new NewTaskDto(
								null,
								project.getId(),
								TaskType.FEATURE,
								TaskPriority.MINOR,
								null,
								null,
								"task_" + i,
								null
						));
					} catch (NoSuchEntityException | AuthorizationException e) {
						e.printStackTrace();
						throw new IllegalStateException();
					}
				})
				.collect(Collectors.toList());
	}
	
	@Test
	public void shouldDeleteTask() throws NoSuchEntityException, AuthorizationException {
		taskController.delete(token, tasks
				.stream()
				.findFirst()
				.orElseThrow(NoSuchEntityException::new).getId());
		
		List<TaskDto> tasksExceptDeleted = taskController.all(token, project.getId(), PageRequest.of(0, Integer.MAX_VALUE));
		
		Assert.assertEquals(tasks.size() - 1, tasksExceptDeleted.size());
	}
	
}
