package com.gmail.ivanjermakov1.projecttracker.core.controller;

import com.gmail.ivanjermakov1.projecttracker.core.dto.EditUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.UserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("profile")
public class ProfileController {
	
	private final UserService userService;
	
	public ProfileController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("edit")
	public UserDto edit(@RequestHeader("token") String token,
	                    @Valid @RequestBody EditUserDto editUserDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(userService.edit(user, editUserDto), UserDto.class);
	}
	
	@GetMapping("/{login}/get")
	public UserDto get(@RequestHeader("token") String token,
	                   @PathVariable String login) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.map(userService.getUser(user, login), UserDto.class);
	}
	
}
