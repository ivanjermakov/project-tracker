package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Role;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.exception.ApiException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role setUserRole(User user, User target, Project project, UserRole role) throws AuthorizationException, ApiException {
		authorize(user, project, UserRole.MODERATOR);
		if (user.getId().equals(target.getId())) throw new ApiException("unable to set or change own role");
		if (role.level >= getRole(user, project).level) throw new ApiException("unable to set role with level higher or equal to own");

		Role newRole = roleRepository.findByUserAndProject(target, project).orElse(new Role(project, target, role));
		newRole.setRole(role);
		return roleRepository.save(newRole);
	}

	public Role getProjectRole(User user, Project project) throws NoSuchEntityException {
		return roleRepository.findByUserAndProject(user, project).orElseThrow(() -> new NoSuchEntityException("user is not registered on project"));
	}

	public List<Role> getProjectRoles(User user, Project project) throws AuthorizationException {
		authorize(user, project, UserRole.MODERATOR);
		return roleRepository.findAllByProject(project);
	}

	public void removeUserRole(User user, User target, Project project) throws AuthorizationException, NoSuchEntityException, ApiException {
		authorize(user, project, UserRole.MODERATOR);
		Role targetRole = roleRepository.findByUserAndProject(target, project).orElseThrow(() -> new NoSuchEntityException("user is not authorized for this project"));
		if (targetRole.getRole() == UserRole.OWNER) throw new ApiException("unable to remove owner");
		if (user.getId().equals(target.getId())) throw new ApiException("unable to remove yourself");

		this.roleRepository.delete(targetRole);
	}

	public UserRole getRole(User user, Project project) {
		Optional<Role> roleOptional = roleRepository.findByUserAndProject(user, project);

		return roleOptional.map(Role::getRole).orElse(UserRole.MODERATOR);
	}

	/**
	 * @param user
	 * @param project
	 * @param role    the lowest accessible role for specified user and operation
	 * @return
	 */
	public boolean hasPermission(User user, Project project, UserRole role) {
		UserRole userRole = getRole(user, project);
		if (!project.getPublic() && userRole.level < UserRole.MEMBER.level) return false;
		return userRole.level >= role.level;
	}

	public void authorize(User user, Project project, UserRole role) throws AuthorizationException {
		if (!hasPermission(user, project, role)) throw new AuthorizationException("no permission");
	}

}
