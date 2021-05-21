package com.gmail.ivanjermakov1.projecttracker.core.controller;

import com.gmail.ivanjermakov1.projecttracker.core.dto.EditTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ListTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.TaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.ProjectService;
import com.gmail.ivanjermakov1.projecttracker.core.service.TaskService;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("task")
public class TaskController {
	
	private final TaskService taskService;
	private final UserService userService;
	private final ProjectService projectService;

	@Autowired
	public TaskController(TaskService taskService, UserService userService, ProjectService projectService) {
		this.taskService = taskService;
		this.userService = userService;
		this.projectService = projectService;
	}
	
	@GetMapping("all")
	public List<TaskDto> all(@RequestHeader("token") String token,
	                         @RequestParam("projectId") Long projectId,
	                         @PageableDefault(direction = Sort.Direction.DESC, sort = {"opened"}) Pageable pageable) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(taskService.all(user, projectId, pageable), TaskDto.class)
				.stream()
				.map(TaskDto::compute)
				.collect(Collectors.toList());
	}
	
	@GetMapping("owned")
	public List<TaskDto> owned(@RequestHeader("token") String token,
	                           @PageableDefault(direction = Sort.Direction.DESC, sort = {"opened"}) Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(taskService.owned(user, pageable), TaskDto.class).stream().map(TaskDto::compute).collect(Collectors.toList());
	}
	
	@GetMapping("assignee")
	public List<TaskDto> assignee(@RequestHeader("token") String token,
	                              @PageableDefault(direction = Sort.Direction.DESC, sort = {"opened"}) Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(taskService.assignee(user, pageable), TaskDto.class).stream().map(TaskDto::compute).collect(Collectors.toList());
	}
	
	@GetMapping("get")
	public TaskDto get(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(taskService.get(user, taskId), TaskDto.class).compute();
	}
	
	@PostMapping("create")
	public TaskDto create(@RequestHeader("token") String token, @Valid @RequestBody NewTaskDto newTaskDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(taskService.create(user, newTaskDto), TaskDto.class).compute();
	}
	
	@PostMapping("edit")
	public TaskDto edit(@RequestHeader("token") String token, @Valid @RequestBody EditTaskDto editTaskDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(taskService.edit(user, editTaskDto), TaskDto.class).compute();
	}
	
	@GetMapping("delete")
	public void delete(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		taskService.delete(user, taskId);
	}

	@PostMapping("{projectId}/list")
	public List<TaskDto> list(@RequestHeader("token") String token,
	                             @PathVariable Long projectId,
	                             @RequestBody ListTaskDto listTaskDto,
	                             @PageableDefault(direction = Sort.Direction.DESC, sort = "created") Pageable pageable) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		Project project = projectService.get(user, projectId);

		return Mapper.mapAll(taskService.list(user, project, listTaskDto), TaskDto.class);
	}

}
