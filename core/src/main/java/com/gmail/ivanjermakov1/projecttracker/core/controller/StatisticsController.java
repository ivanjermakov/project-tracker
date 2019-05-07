package com.gmail.ivanjermakov1.projecttracker.core.controller;

import com.gmail.ivanjermakov1.projecttracker.core.dto.stats.ProjectActivityDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.stats.ProjectTaskTypeDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.ProjectService;
import com.gmail.ivanjermakov1.projecttracker.core.service.StatisticsService;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stats")
public class StatisticsController {
	
	private final UserService userService;
	private final ProjectService projectService;
	private final StatisticsService statisticsService;
	
	@Autowired
	public StatisticsController(UserService userService, ProjectService projectService, StatisticsService statisticsService) {
		this.userService = userService;
		this.projectService = projectService;
		this.statisticsService = statisticsService;
	}
	
	@GetMapping("project-activity")
	public List<ProjectActivityDto> getProjectActivities(@RequestHeader("token") String token,
	                                                     @RequestParam("projectId") Long projectId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		Project project = projectService.get(user, projectId);
		
		return statisticsService.getProjectActivities(user, project);
	}
	
	@GetMapping("project-task-type")
	public List<ProjectTaskTypeDto> getProjectTaskTypes(@RequestHeader("token") String token,
	                                                    @RequestParam("projectId") Long projectId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		Project project = projectService.get(user, projectId);
		
		return statisticsService.getProjectTaskTypes(user, project);
	}
	
}
