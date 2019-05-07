package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.stats.ProjectActivityDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.entity.nontable.ProjectActivity;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.ActivityRepository;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
	
	private final ActivityService activityService;
	private final ActivityRepository activityRepository;
	private final RoleService roleService;
	
	@Autowired
	public StatisticsService(ActivityService activityService, ActivityRepository activityRepository, RoleService roleService) {
		this.activityService = activityService;
		this.activityRepository = activityRepository;
		this.roleService = roleService;
	}
	
	public List<ProjectActivityDto> getProjectActivities(User user, Project project) throws NoSuchEntityException, AuthorizationException {
//		List<Activity> activities = activityService.allByProject(
//				user, project.getId(), PageRequest.of(0, Integer.MAX_VALUE, Sort.Direction.ASC, "timestamp")
//		);
		
		roleService.authorize(user, project, UserRole.VIEWER);
		
		List<ProjectActivity> activities = activityRepository.findActivitiesByProject(project.getId());
		return Mapper.mapAll(activities, ProjectActivityDto.class);
	}
	
}
