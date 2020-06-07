package com.gmail.ivanjermakov1.projecttracker.core;

import com.gmail.ivanjermakov1.projecttracker.core.controller.ActivityController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.AuthController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.ProjectController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.RegisterController;
import com.gmail.ivanjermakov1.projecttracker.core.controller.TaskController;
import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.RegistrationException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private ActivityController activityController;
	@Autowired
	private TaskController taskController;

	//	@Before
	public void fillDb() {
		IntStream.range(0, 10000).forEach(i -> {
			try {
				RegisterUserDto registerUserDto = new RegisterUserDto(UUID.randomUUID().toString(), "password");
				registerController.register(registerUserDto);
				String token = authController.authenticate(new AuthUserDto(registerUserDto.login, registerUserDto.password));
				ProjectDto projectDto = projectController.create(UUID.randomUUID().toString(), new NewProjectDto(true, "test_proj" + i, null, null));
				taskController.create(token, new NewTaskDto(null, projectDto.id, TaskType.FEATURE, null, null, UUID.randomUUID().toString(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
//	@Ignore
	public void activity() throws RegistrationException, AuthenticationException, NoSuchEntityException {
		RegisterUserDto registerUserDto = new RegisterUserDto("test", "password");
		registerController.register(registerUserDto);
		String token = authController.authenticate(new AuthUserDto(registerUserDto.login, registerUserDto.password));

		IntStream.range(0, 1000).forEach(i -> {
			try {
				activityController.allByUser(token, Pageable.unpaged());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@Test
//	@Ignore
	public void searchTask() throws RegistrationException, NoSuchEntityException, AuthenticationException {
		RegisterUserDto registerUserDto = new RegisterUserDto("test", "password");
		registerController.register(registerUserDto);
		String token = authController.authenticate(new AuthUserDto(registerUserDto.login, registerUserDto.password));

		IntStream.range(0, 1000).forEach(i -> {
			try {
				taskController.owned(token, Pageable.unpaged());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}


}
