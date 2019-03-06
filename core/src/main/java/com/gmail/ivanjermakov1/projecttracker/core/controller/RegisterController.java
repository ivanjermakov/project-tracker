package com.gmail.ivanjermakov1.projecttracker.core.controller;


import com.gmail.ivanjermakov1.projecttracker.core.dto.RegisterUserDto;
import com.gmail.ivanjermakov1.projecttracker.core.entity.UserCredentials;
import com.gmail.ivanjermakov1.projecttracker.core.exeption.RegistrationException;
import com.gmail.ivanjermakov1.projecttracker.core.repository.UserCredentialsRepository;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {
	
	private final UserService userService;
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	
	
	@Autowired
	public RegisterController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public void register(@RequestBody RegisterUserDto registerUserDto) throws RegistrationException {
		userService.register(registerUserDto);
	}
	
	@GetMapping
	public Iterable<UserCredentials> test() {
		return userCredentialsRepository.findAll();
	}
	
}
