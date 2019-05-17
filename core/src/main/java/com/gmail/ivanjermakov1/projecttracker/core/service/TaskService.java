package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.EditTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewActivityDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.TaskInfo;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.ProjectRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {
	
	private final ProjectService projectService;
	
	private final RoleService roleService;
	
	
	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;
	private final ActivityService activityService;
	
	@Autowired
	public TaskService(ProjectService projectService, TaskRepository taskRepository, RoleService roleService, ProjectRepository projectRepository, ActivityService activityService) {
		this.projectService = projectService;
		this.taskRepository = taskRepository;
		this.roleService = roleService;
		this.projectRepository = projectRepository;
		this.activityService = activityService;
	}
	
	/**
	 * Create new task. By default also create activity with status {@code TaskStatus.OPEN}
	 *
	 * @param user       User
	 * @param newTaskDto NewTaskDto
	 * @return Created task
	 * @throws NoSuchEntityException  if parent task with such id does not exist
	 * @throws AuthorizationException if no access. Required role is {@code UserRole.COLLABORATOR}
	 */
	public Task create(User user, NewTaskDto newTaskDto) throws NoSuchEntityException, AuthorizationException {
		Task task = new Task(
				projectService.get(user, newTaskDto.projectId),
				null,
				user,
				newTaskDto.type,
				newTaskDto.estimate,
				0d,
				LocalDateTime.now(),
				newTaskDto.due,
				null,
				Collections.emptyList()
		);
		
		if (newTaskDto.parentTaskId != null) {
			Task parentTask = taskRepository.findById(newTaskDto.parentTaskId).orElseThrow(() -> new NoSuchElementException("no such parent task"));
			task.setParent(parentTask);
		}
		
		task = taskRepository.save(task);
		
		task.setTaskInfo(new TaskInfo(
				task,
				newTaskDto.name,
				newTaskDto.description
		));
		
		activityService.create(user, new NewActivityDto(
				task.getId(),
				TaskStatus.OPEN,
				null,
				null,
				null
		));
		
		task = taskRepository.save(task);
		
		return task;
	}
	
	public Task edit(User user, EditTaskDto editTaskDto) throws NoSuchEntityException, AuthorizationException {
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
	
	public Task get(User user, Long taskId) throws AuthorizationException, NoSuchEntityException {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("no such task"));
		
		roleService.authorize(user, task.getProject(), UserRole.VIEWER);
		
		return task;
	}
	
}
