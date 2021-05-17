package com.gmail.ivanjermakov1.projecttracker.core.controller;

import com.gmail.ivanjermakov1.projecttracker.core.dto.RoleDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.SetRoleDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.ApiException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.ProjectService;
import com.gmail.ivanjermakov1.projecttracker.core.service.RoleService;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

	private final RoleService roleService;
	private final UserService userService;
	private final ProjectService projectService;

	@Autowired
	public RoleController(RoleService roleService, UserService userService, ProjectService projectService) {
		this.roleService = roleService;
		this.userService = userService;
		this.projectService = projectService;
	}

	@PostMapping
	public RoleDto setUserRole(@RequestHeader("token") String token, @RequestBody SetRoleDto setRoleDto) throws AuthorizationException, NoSuchEntityException, ApiException {
		User user = userService.validate(token);
		User target = userService.getUser(user, setRoleDto.getLogin());
		Project project = projectService.get(user, setRoleDto.getProjectId());

		return Mapper.map(
				roleService.setUserRole(user, target, project, setRoleDto.getRole()),
				RoleDto.class
		);
	}

	@DeleteMapping("{projectId}/{login}")
	public void removeUserRole(@RequestHeader("token") String token, @PathVariable Long projectId, @PathVariable String login) throws NoSuchEntityException, AuthorizationException, ApiException {
		User user = userService.validate(token);
		User target = userService.getUser(user, login);
		Project project = projectService.get(user, projectId);

		roleService.removeUserRole(user, target, project);
	}

	@GetMapping("{projectId}")
	public RoleDto getProjectRole(@RequestHeader("token") String token, @PathVariable Long projectId) throws AuthorizationException, NoSuchEntityException {
		User user = userService.validate(token);
		Project project = projectService.get(user, projectId);
		return Mapper.map(
				roleService.getProjectRole(user, project),
				RoleDto.class
		);
	}

	@GetMapping("{projectId}/all")
	public List<RoleDto> getProjectRoles(@RequestHeader("token") String token, @PathVariable Long projectId) throws AuthorizationException, NoSuchEntityException {
		User user = userService.validate(token);
		Project project = projectService.get(user, projectId);
		return Mapper.mapAll(
				roleService.getProjectRoles(user, project),
				RoleDto.class
		);
	}

}
