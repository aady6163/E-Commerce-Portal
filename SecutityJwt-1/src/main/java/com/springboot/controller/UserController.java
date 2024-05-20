package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.ChangePassword;
import com.springboot.entity.User;
import com.springboot.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

//	@PostMapping("/registerNewUser")
//	public User registerNewUser(@RequestBody User user) {
//		return userService.registerNewUser(user);
//	}
	
	@PostMapping({"/registerNewUser"})
	public User registerNewUser(@RequestBody User user) {
	return	userService.registerNewUser(user);
	}
	
//	@PostConstruct
//	public void iniitRolesAndusers() {
//		userService.initRolesAndUser();
//	}
	
	@GetMapping("/forAdmin")
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "This is only accessible to admin";
	}
	
	@GetMapping("/forUser")
	@PreAuthorize("hasRole('User')")
	public String forUser() {
		return "This is only accessible to user";
	}
	
	
	@GetMapping("/getUser")
	@PreAuthorize("hasRole('User')")
	public User getUser() {
	return	userService.getUser();
	
	}
	
	
	@PostMapping("/changePassword")
	@PreAuthorize("hasRole('User')")
	public boolean changePassword(@RequestBody ChangePassword changePassword) {
	
		
		return userService.changePassword(changePassword);
	}
}
