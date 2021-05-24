package com.gmail.ivanjermakov1.projecttracker.core.controller;

import com.gmail.ivanjermakov1.projecttracker.core.dto.UserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{login}/follow")
	public void follow(@RequestHeader("token") String token,
	                   @PathVariable String login,
	                   @RequestParam("follow") Boolean follow) throws NoSuchEntityException {
		User user = userService.validate(token);
		User followUser = userService.getUser(user, login);
		userService.follow(user, followUser, follow);
	}

	@GetMapping("/{login}/followers")
	public Set<UserDto> getFollowers(@RequestHeader("token") String token,
	                                 @PathVariable String login) throws NoSuchEntityException {
		User user = userService.validate(token);
		User targetUser = userService.getUser(user, login);
		return new HashSet<>(Mapper.mapAll(targetUser.getFollowers(), UserDto.class));
	}

	@GetMapping("/{login}/following")
	public Set<UserDto> getFollowing(@RequestHeader("token") String token,
	                                 @PathVariable String login) throws NoSuchEntityException {
		User user = userService.validate(token);
		User targetUser = userService.getUser(user, login);
		return new HashSet<>(Mapper.mapAll(targetUser.getFollowing(), UserDto.class));
	}

	@GetMapping("find")
	public List<UserDto> find(@RequestHeader("token") String token,
	                          @RequestParam("query") String query) throws NoSuchEntityException {
		User user = userService.validate(token);
		return Mapper.mapAll(userService.find(user, query), UserDto.class);
	}

}
