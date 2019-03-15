package com.gmail.ivanjermakov1.projecttracker.core.controller;


import com.gmail.ivanjermakov1.projecttracker.core.dto.NewProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.DuplicationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.ProjectService;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@GetMapping("all")
	public List<ProjectDto> all(@RequestHeader("token") String token, Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return projectService.all(user, pageable)
				.stream()
				.map(p -> Mapper.map(p, 0))
				.sorted(Comparator.comparing(p -> p.created))
				.collect(Collectors.toList());
	}
	
	@GetMapping("get")
	public ProjectDto get(@RequestHeader("token") String token,
	                      @RequestParam("login") String login,
	                      @RequestParam("name") String name) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.map(projectService.get(user, login, name), 0);
	}
	
}
