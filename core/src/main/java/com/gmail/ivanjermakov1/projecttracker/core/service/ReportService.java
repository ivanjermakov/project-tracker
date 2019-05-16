package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.ActivityDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReportService {
	
	@Value("${web.static.resources.path}")
	private String resourcesPath;
	
	@Value("${web.static.resources.stylesheet}")
	private String stylesheetPath;
	
	@Value("${web.static.resources.stylesheet.extension}")
	private String stylesheetExtension;
	
	private final ProjectService projectService;
	private final SpreadsheetService spreadsheetService;
	private final ActivityService activityService;
	
	
	@Autowired
	public ReportService(ProjectService projectService, SpreadsheetService spreadsheetService, ActivityService activityService) {
		this.projectService = projectService;
		this.spreadsheetService = spreadsheetService;
		this.activityService = activityService;
	}
	
	public String createActivityReport(User user, Long projectId) throws AuthorizationException, NoSuchEntityException, IOException {
		Project project = projectService.get(user, projectId);
		
		String reportName = spreadsheetService.createActivityReport(
				Mapper.map(project, ProjectDto.class),
				Mapper.mapAll(activityService.allByProject(user, projectId, PageRequest.of(0, Integer.MAX_VALUE)), ActivityDto.class)
		);
		
		return resourcesPath + stylesheetPath + projectId + "/" + reportName + stylesheetExtension;
	}
	
}