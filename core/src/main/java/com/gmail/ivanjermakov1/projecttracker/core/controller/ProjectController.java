package com.gmail.ivanjermakov1.projecttracker.core.controller;


import com.gmail.ivanjermakov1.projecttracker.core.dto.EditProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.DuplicationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.ProjectService;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import com.gmail.ivanjermakov1.projecttracker.core.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {
	
	private final UserService userService;
	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(UserService userService, ProjectService projectService) {
		this.userService = userService;
		this.projectService = projectService;
	}
	
	@PostMapping("create")
	public ProjectDto create(@RequestHeader("token") String token, @RequestBody NewProjectDto newProjectDto) throws NoSuchEntityException, DuplicationException {
		User user = userService.validate(token);
		
		return projectService.create(user, newProjectDto);
	}
	
	@PostMapping("edit")
	public ProjectDto edit(@RequestHeader("token") String token, @RequestBody EditProjectDto editProjectDto) throws NoSuchEntityException, DuplicationException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(projectService.edit(user, editProjectDto), ProjectDto.class);
	}
	
	@GetMapping("all")
	public List<ProjectDto> all(@RequestHeader("token") String token, Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Streams.sortList(
				Mapper.mapAll(projectService.all(user, pageable), ProjectDto.class),
				Comparator.comparing(p -> p.created)
		);
	}
	
	@GetMapping("/{login}/{name}/get")
	public ProjectDto get(@RequestHeader("token") String token,
	                      @PathVariable String login,
	                      @PathVariable String name) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(projectService.get(user, login, name), ProjectDto.class);
	}
	
}
