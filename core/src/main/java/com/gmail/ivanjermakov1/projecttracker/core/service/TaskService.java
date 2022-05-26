package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.EditTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.ListTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Activity;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Project;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.TaskInfo;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.TaskStatus;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.ActivityRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.ProjectRepository;
import com.gmail.ivanjermakov1.projecttracker.core.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

	private final ProjectService projectService;
	private final RoleService roleService;
	private final TaskRepository taskRepository;
	private final ProjectRepository projectRepository;
	private final ActivityRepository activityRepository;

	@Autowired
	public TaskService(ProjectService projectService, TaskRepository taskRepository, RoleService roleService, ProjectRepository projectRepository, ActivityRepository activityRepository) {
		this.projectService = projectService;
		this.taskRepository = taskRepository;
		this.roleService = roleService;
		this.projectRepository = projectRepository;
		this.activityRepository = activityRepository;
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
		Project project = projectService.get(user, newTaskDto.getProjectId());

		roleService.authorize(user, project, UserRole.MEMBER);

		Task task = new Task(
				null,
				project,
				null,
				user,
				newTaskDto.getType(),
				TaskStatus.OPEN,
				newTaskDto.getPriority(),
				null,
				newTaskDto.getEstimate(),
				0d,
				LocalDateTime.now(),
				Optional.ofNullable(newTaskDto.getStarted()).orElse(LocalDate.now()),
				newTaskDto.getDue(),
                newTaskDto.getFullName(),
                newTaskDto.getBirthDate(),
                newTaskDto.getHeight(),
                newTaskDto.getWeight(),
                newTaskDto.getMedicalHistory(),
                newTaskDto.getTookMedicine(),
				null,
				new ArrayList<>(),
				new ArrayList<>(),
				null,
				null
		);
		task.setStatus(TaskStatus.OPEN);

		if (newTaskDto.getParentTaskId() != null) {
			Task parentTask = taskRepository.findById(newTaskDto.getParentTaskId()).orElseThrow(() -> new NoSuchElementException("no such parent task"));
			task.setParent(parentTask);
		}

		task = taskRepository.save(task);

		task.setTaskInfo(new TaskInfo(
				null,
				task,
				newTaskDto.getName(),
				newTaskDto.getDescription()
		));

        Activity activity = new Activity();
        activity.setTask(task);
        activity.setCreator(user);
        activity.setTimestamp(LocalDateTime.now());
        activity.setStatus(TaskStatus.OPEN);
        task.getActivities().add(activity);

		return taskRepository.save(task);
	}

	public Task edit(User user, EditTaskDto editTaskDto) throws NoSuchEntityException, AuthorizationException {
		Task task = taskRepository.findById(editTaskDto.getId()).orElseThrow(() -> new NoSuchEntityException("no such task to edit"));

		roleService.authorize(user, task.getProject(), UserRole.MEMBER);

		TaskInfo taskInfo = task.getTaskInfo();
		taskInfo.setName(editTaskDto.getName());
		taskInfo.setDescription(editTaskDto.getDescription());
		task.setTaskInfo(taskInfo);
		task.setTag(editTaskDto.getTag());

		return taskRepository.save(task);
	}

	public void delete(User user, Long taskId) throws NoSuchEntityException, AuthorizationException {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchEntityException("no such task to edit"));

		roleService.authorize(user, task.getProject(), UserRole.MEMBER);

		taskRepository.delete(task);
	}

	public List<Task> all(User user, Long projectId, Pageable pageable) throws AuthorizationException {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchElementException("no such project"));

		roleService.authorize(user, project, UserRole.MEMBER);

		return taskRepository.findAllByProject(project, pageable);
	}

	public Task get(User user, Long taskId) throws AuthorizationException {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("no such task"));

		roleService.authorize(user, task.getProject(), UserRole.MEMBER);

		return task;
	}

	public List<Task> owned(User user, Pageable pageable) {
		return taskRepository.findAllByCreator(user, pageable);
	}

	public List<Task> assignee(User user, Pageable pageable) {
		return taskRepository.findAllByAssignee(user, pageable);
	}

	public List<Task> list(User user, Project project, ListTaskDto listTaskDto) throws AuthorizationException {
		List<Task> tasks = taskRepository.findAllByProject(project, PageRequest.of(0, Integer.MAX_VALUE));
		return tasks.stream()
				.filter(listTaskDto)
				.collect(Collectors.toList());
	}

}
