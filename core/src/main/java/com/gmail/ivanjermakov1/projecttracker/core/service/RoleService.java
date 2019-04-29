package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Role;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public UserRole getRole(User user, Project project) {
		Optional<Role> roleOptional = roleRepository.findByUserAndProject(user, project);
		
		if (!roleOptional.isPresent()) return UserRole.UNAUTHORIZED;
		
		return roleOptional.get().getRole();
	}
	
	/**
	 * @param user
	 * @param project
	 * @param role    the lowest accessible role for specified user and operation
	 * @return
	 */
	public boolean hasPermission(User user, Project project, UserRole role) {
		if (!project.getPublic() && role == UserRole.UNAUTHORIZED) return false;
		return getRole(user, project).level >= role.level;
	}
	
	public void authorize(User user, Project project, UserRole role) throws AuthorizationException {
		if (!hasPermission(user, project, role)) throw new AuthorizationException("no permission");
	}
	
}
