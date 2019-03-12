package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.NewProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.ProjectInfo;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.DuplicationException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.ProjectRepository;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {
	
	private final ProjectRepository projectRepository;
	
	@Autowired
	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	public ProjectDto create(User user, NewProjectDto newProjectDto) throws DuplicationException {
		if (projectRepository.findByNameAndUser(newProjectDto.name, user.getId()) != null)
			throw new DuplicationException("such project already exist");
		
		Project project = projectRepository.save(
				new Project(
						user,
						newProjectDto.isPublic,
						LocalDateTime.now()
				)
		);
		
		ProjectInfo projectInfo = new ProjectInfo(
				project,
				newProjectDto.name,
				newProjectDto.description,
				newProjectDto.about
		);
		
		project.setProjectInfo(projectInfo);
		
		projectRepository.save(project);

//		TODO: feature: progress
		return Mapper.map(project, 0);
	}
	
	public List<Project> all(User user, Pageable pageable) {
		return projectRepository.findAllByUser(user, pageable);
	}
	
}
