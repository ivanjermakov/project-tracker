package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.EditTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.TaskInfo;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.ProjectRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
	
	private final ProjectService projectService;
	
	private final TaskRepository taskRepository;
	private final RoleService roleService;
	private final ProjectRepository projectRepository;
	
	@Autowired
	public TaskService(ProjectService projectService, TaskRepository taskRepository, RoleService roleService, ProjectRepository projectRepository) {
		this.projectService = projectService;
		this.taskRepository = taskRepository;
		this.roleService = roleService;
		this.projectRepository = projectRepository;
	}
	
	public Task create(User user, NewTaskDto newTaskDto) throws NoSuchEntityException, AuthorizationException {
		Task task = new Task(
				projectService.get(user, newTaskDto.projectId),
				user,
				newTaskDto.type,
				newTaskDto.estimate,
				0d,
				LocalDateTime.now(),
				newTaskDto.due,
				null,
				Collections.emptyList()
		);
		
		task = taskRepository.save(task);
		
		task.setTaskInfo(new TaskInfo(
				task,
				newTaskDto.name,
				newTaskDto.description
		));
		
		if (newTaskDto.parentTaskId != null) {
			Task parentTask = taskRepository.findById(newTaskDto.parentTaskId).orElseThrow(() -> new NoSuchElementException("no such parent task"));
			parentTask.getSubtasks().add(task);
			taskRepository.save(parentTask);
		}
		
		return taskRepository.save(task);
	}
	
	public Task edit(User user, EditTaskDto editTaskDto) throws NoSuchEntityException, AuthorizationException {
//		TODO: dto validation
		
		Task task = taskRepository.findById(editTaskDto.id).orElseThrow(() -> new NoSuchEntityException("no such task to edit"));
		
		roleService.authorize(user, task.getProject(), UserRole.COLLABORATOR);
		
		TaskInfo taskInfo = task.getTaskInfo();
		taskInfo.setName(editTaskDto.name);
		taskInfo.setDescription(editTaskDto.description);
		task.setTaskInfo(taskInfo);
		
		return taskRepository.save(task);
	}
	
	public void delete(User user, Long taskId) throws NoSuchEntityException, AuthorizationException {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchEntityException("no such task to edit"));
		
		if (!(roleService.hasPermission(user, task.getProject(), UserRole.COLLABORATOR) ||
				(task.getCreator().getId().equals(user.getId())))) throw new AuthorizationException("no permission");
		
		taskRepository.delete(task);
	}
	
	public List<Task> all(User user, Long projectId, Pageable pageable) throws AuthorizationException {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException("no such project"));
		
		roleService.authorize(user, project, UserRole.VIEWER);
		
		return taskRepository.findAllByProject(project, pageable);
	}
	
}
