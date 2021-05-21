package com.gmail.ivanjermakov1.projecttracker.core.controller;

import com.gmail.ivanjermakov1.projecttracker.core.dto.CommentDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.NewCommentDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.Task;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.CommentService;
import com.gmail.ivanjermakov1.projecttracker.core.service.TaskService;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {

	private final TaskService taskService;
	private final UserService userService;
	private final CommentService commentService;

	@GetMapping
	public List<CommentDto> getAll(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		Task task = taskService.get(user, taskId);

		return Mapper.mapAll(commentService.getAll(user, task), CommentDto.class);
	}

	@PostMapping
	public CommentDto post(@RequestHeader("token") String token, @Valid @RequestBody NewCommentDto newCommentDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);

		return Mapper.map(commentService.post(user, newCommentDto), CommentDto.class);
	}

}
