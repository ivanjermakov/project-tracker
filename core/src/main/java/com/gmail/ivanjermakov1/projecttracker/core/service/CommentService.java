package com.gmail.ivanjermakov1.projecttracker.core.service;

import com.gmail.ivanjermakov1.projecttracker.core.dto.NewCommentDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Comment;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.entity.enums.UserRole;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final TaskService taskService;
	private final RoleService roleService;
	private final CommentRepository commentRepository;

	public List<Comment> getAll(User user, Task task) throws AuthorizationException {
		roleService.authorize(user, task.getProject(), UserRole.MEMBER);

		return commentRepository.findAllByTask(task);
	}

	public Comment post(User user, NewCommentDto newCommentDto) throws AuthorizationException {
		Task task = taskService.get(user, newCommentDto.getTaskId());

		return commentRepository.save(new Comment(null, task, user, newCommentDto.getText(), LocalDateTime.now()));
	}
}
