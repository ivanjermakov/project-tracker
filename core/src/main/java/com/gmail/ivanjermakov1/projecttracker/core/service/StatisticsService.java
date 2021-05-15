package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.stats.ProjectActivityDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.stats.ProjectTaskTypeDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.Period;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
		roleService.authorize(user, project, UserRole.MEMBER);
		
		List<ProjectActivity> activities = activityRepository.findActivitiesByProject(project.getId());
		return format(
				Mapper.mapAll(activities, ProjectActivityDto.class),
				Period.DAILY,
				true
		);
	}
	
	public List<ProjectTaskTypeDto> getProjectTaskTypes(User user, Project project) throws AuthorizationException {
		roleService.authorize(user, project, UserRole.MEMBER);
		
		List<ProjectTaskType> taskTypes = taskRepository.findTaskTypesByProject(project.getId());
		return taskTypes
				.stream()
				.map(t -> new ProjectTaskTypeDto(
						TaskType.values()[t.getType()],
						t.getCount()
				))
				.collect(Collectors.toList());
	}
	
	public List<ProjectActivityDto> format(List<ProjectActivityDto> activities, Period period, boolean emptyFill) {
		if (emptyFill) {
			activities = fillEmptyDays(activities);
		}
		
		if (activities.size() == 0) return Collections.emptyList();
		
		List<LocalDate> resultDates = generateDates(
				activities.get(0).day,
				activities.get(activities.size() - 1).day,
				period
		);
		
		List<List<ProjectActivityDto>> groups = groupByPeriod(activities, resultDates);
		
		return groups.stream()
				.map(group -> new ProjectActivityDto(
						group.get(0).day,
						group
								.stream()
								.mapToInt(a -> a.activityAmount)
								.sum()
				))
				.collect(Collectors.toList());
	}
	
	private List<List<ProjectActivityDto>> groupByPeriod(List<ProjectActivityDto> activities, List<LocalDate> startDates) {
		List<List<ProjectActivityDto>> result = new ArrayList<>();
		
		for (int i = 0; i < startDates.size(); i++) {
			List<ProjectActivityDto> group = new ArrayList<>();
			for (ProjectActivityDto a : activities) {
				if (!a.day.isBefore(startDates.get(i)) &&
						(i == startDates.size() - 1 || a.day.isBefore(startDates.get(i + 1)))) {
					group.add(a);
				}
			}
			result.add(group);
		}
		
		return result;
	}
	
	private List<ProjectActivityDto> fillEmptyDays(List<ProjectActivityDto> activities) {
		if (activities.isEmpty()) return activities;
		List<ProjectActivityDto> result = new ArrayList<>();
		
		LocalDate currentDate = activities.get(0).day;
		LocalDate toDate = activities.get(activities.size() - 1).day;
		
		while (!currentDate.isAfter(toDate)) {
			LocalDate finalCurrentDate = currentDate;
			
			Optional<ProjectActivityDto> current = activities
					.stream()
					.filter(a -> a.day.equals(finalCurrentDate))
					.findFirst();
			
			if (current.isPresent()) {
				result.add(current.get());
			} else {
				result.add(new ProjectActivityDto(currentDate, 0));
			}
			
			currentDate = currentDate.plusDays(1);
		}
		
		return result;
	}
	
	private List<LocalDate> generateDates(LocalDate from, LocalDate to, Period period) {
		List<LocalDate> result = new ArrayList<>();
		LocalDate current = from;
		
		while (!current.isAfter(to)) {
			result.add(current);
			
			switch (period) {
				case DAILY:
					current = current.plusDays(1);
					break;
				case WEEKLY:
					current = current.plusWeeks(1);
					break;
				case MONTHLY:
					current = current.plusMonths(1);
					break;
				case YEARLY:
					current = current.plusYears(1);
					break;
			}
		}
		
		return result;
	}
	
}
