package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.stats.ProjectActivityDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.stats.ProjectTaskTypeDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskType;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.entity.nontable.ProjectActivity;
import com.gmail.ivanjermakov1.projecttracker.core.entity.nontable.ProjectTaskType;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.ActivityRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.TaskRepository;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {
	
	private final ActivityRepository activityRepository;
	
	private final RoleService roleService;
	private final TaskRepository taskRepository;
	
	@Autowired
	public StatisticsService(ActivityRepository activityRepository, RoleService roleService, TaskRepository taskRepository) {
		this.activityRepository = activityRepository;
		this.roleService = roleService;
		this.taskRepository = taskRepository;
	}
	
	public List<ProjectActivityDto> getProjectActivities(User user, Project project) throws AuthorizationException {
		roleService.authorize(user, project, UserRole.VIEWER);
		
		List<ProjectActivity> activities = activityRepository.findActivitiesByProject(project.getId());
		return Mapper.mapAll(activities, ProjectActivityDto.class);
	}
	
	public List<ProjectTaskTypeDto> getProjectTaskTypes(User user, Project project) throws AuthorizationException {
		roleService.authorize(user, project, UserRole.VIEWER);
		
		List<ProjectTaskType> taskTypes = taskRepository.findTaskTypesByProject(project.getId());
		return taskTypes
				.stream()
				.map(t -> new ProjectTaskTypeDto(
						TaskType.values()[t.getType()],
						t.getCount()
				))
				.collect(Collectors.toList());
	}
	
}
