package com.gmail.ivanjermakov1.projecttracker.core.controller;

import com.gmail.ivanjermakov1.projecttracker.core.entity.User;
import com.gmail.ivanjermakov1.projecttracker.core.exception.AuthorizationException;
import com.gmail.ivanjermakov1.projecttracker.core.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.projecttracker.core.service.ReportService;
import com.gmail.ivanjermakov1.projecttracker.core.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("report")
public class ReportController {
	
	private final ReportService reportService;
	private final UserService userService;
	
	public ReportController(ReportService reportService, UserService userService) {
		this.reportService = reportService;
		this.userService = userService;
	}
	
	@GetMapping("activity")
	public String createActivityReport(@RequestHeader("token") String token,
	                                   @RequestParam("projectId") Long projectId) throws NoSuchEntityException, AuthorizationException, IOException {
		User user = userService.validate(token);
		
		return reportService.createActivityReport(user, projectId);
	}
	
}
