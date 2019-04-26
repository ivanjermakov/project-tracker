package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.EditTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewTaskDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.TaskInfo;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@Transactional
public class TaskService {
	
	private final ProjectService projectService;
	private final TaskRepository taskRepository;
	
	@Autowired
	public TaskService(ProjectService projectService, @Qualifier("taskRepository") TaskRepository taskRepository) {
		this.projectService = projectService;
		this.taskRepository = taskRepository;
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
				newTaskDto.name,
				newTaskDto.description,
				task
		));
		
		return taskRepository.save(task);
	}
	
	public Task edit(User user, EditTaskDto editTaskDto) throws NoSuchEntityException {
//		TODO: authorization
//		TODO: dto validation
		
		Task task = taskRepository.findById(editTaskDto.id).orElseThrow(() -> new NoSuchEntityException("no such task to edit"));
		
		task.setType(editTaskDto.type);
		task.setEstimate(editTaskDto.estimate);
		task.setDue(editTaskDto.due);
		TaskInfo taskInfo = task.getTaskInfo();
		taskInfo.setName(editTaskDto.name);
		taskInfo.setDescription(editTaskDto.description);
		task.setTaskInfo(taskInfo);
		
		return taskRepository.save(task);
	}
	
}
