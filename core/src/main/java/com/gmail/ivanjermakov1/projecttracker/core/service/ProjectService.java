package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.EditProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ProjectDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.ProjectInfo;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Role;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.DuplicationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.ProjectRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.RoleRepository;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

	private final RoleService roleService;
	private final ProjectRepository projectRepository;
	private final RoleRepository roleRepository;

	@Autowired
	public ProjectService(ProjectRepository projectRepository, RoleRepository roleRepository, RoleService roleService) {
		this.projectRepository = projectRepository;
		this.roleRepository = roleRepository;
		this.roleService = roleService;
	}

	public ProjectDto create(User user, NewProjectDto newProjectDto) throws DuplicationException {
		if (projectRepository.findByNameAndUser(newProjectDto.getName(), user.getId()).isPresent())
			throw new DuplicationException("such project already exist");

		Project project = projectRepository.save(
				new Project(
						null,
						user,
						newProjectDto.getIsPublic(),
						LocalDateTime.now(),
						null,
						new ArrayList<>(),
						new ArrayList<>(),
						null,
						null
				)
		);

		ProjectInfo projectInfo = new ProjectInfo(
				null,
				project,
				newProjectDto.getName(),
				newProjectDto.getDescription(),
				newProjectDto.getAbout()
		);

		project.setProjectInfo(projectInfo);

		projectRepository.save(project);

		roleRepository.save(new Role(null, project, user, UserRole.OWNER));

//		TODO: feature: progress
		return Mapper.map(project, ProjectDto.class);
	}

	public Project edit(User user, EditProjectDto editProjectDto) throws NoSuchEntityException, AuthorizationException {
		Project project = this.projectRepository.findById(editProjectDto.getId()).orElseThrow(() -> new NoSuchEntityException("no such project to edit"));

		roleService.authorize(user, project, UserRole.MODERATOR);

		project.setIsPublic(editProjectDto.getIsPublic());
		project.getProjectInfo().setName(editProjectDto.getName());
		project.getProjectInfo().setDescription(editProjectDto.getDescription());
		project.getProjectInfo().setAbout(editProjectDto.getAbout());

		return projectRepository.save(project);
	}

	public Project get(User user, String login, String name) throws NoSuchEntityException, AuthorizationException {
		NoSuchEntityException noSuchProjectException = new NoSuchEntityException("no such project");

		Project project = projectRepository.findByLoginAndName(login, name).orElseThrow(() -> noSuchProjectException);

		if (!roleService.hasPermission(user, project, UserRole.MEMBER)) throw noSuchProjectException;

		return project;
	}

	public Project get(User user, Long projectId) throws NoSuchEntityException, AuthorizationException {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchEntityException("no such project"));

		return get(user, project.getUser().getUserCredentials().getLogin(), project.getProjectInfo().getName());
	}


	public List<Project> all(User user, Pageable pageable) {
		return all(user, user, pageable);
	}

	public List<Project> all(User user, User ofUser, Pageable pageable) {
		return projectRepository.findAllByUser(ofUser.getId(), pageable).stream()
				.filter(p -> {
					if (user.getId().equals(ofUser.getId())) return true;
					return p.getIsPublic();
				})
				.collect(Collectors.toList());
	}

	public void delete(User user, Long projectId) throws NoSuchEntityException, AuthorizationException {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchEntityException("no such project"));

		roleService.authorize(user, project, UserRole.OWNER);

		projectRepository.delete(project);
	}

	public List<Project> find(User user, String search, Pageable pageable) {
		return projectRepository.find(user, search, pageable);
	}

}
