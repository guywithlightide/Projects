package com.connectus.platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.connectus.platform.models.User;
import com.connectus.platform.services.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/{id}")
	public User getUserById(@PathVariable(name = "id") String id) {
		return userService.getUserById(id);
	}
	
	@PostMapping(path = "/add")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
}
