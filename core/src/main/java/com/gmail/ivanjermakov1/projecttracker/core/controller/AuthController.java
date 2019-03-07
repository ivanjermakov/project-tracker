package com.gmail.ivanjermakov1.projecttracker.core.controller;

import com.gmail.ivanjermakov1.projecttracker.core.dto.AuthUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.dto.UserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.AuthenticationException;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import com.gmail.ivanjermakov1.projecttracker.core.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	private final UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public String authenticate(@RequestBody AuthUserDto authUserDto) throws AuthenticationException, NoSuchEntityException {
		return userService.authenticate(authUserDto);
	}
	
	@GetMapping("validate")
	public UserDto validate(@RequestHeader("Auth-Token") String token) throws NoSuchEntityException {
		User user = userService.validate(token);
		return Mapper.map(user);
	}
	
}
