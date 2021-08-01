package com.micros.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micros.userservice.entities.User;
import com.micros.userservice.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);		
	}
	
	@GetMapping("/{userId}")
	@CircuitBreaker(name = "userFallBack",fallbackMethod = "getUserWithDeptFallBack")
	public ResponseEntity<Object> getUserWithDept(@PathVariable("userId") Long userId) {
		return ResponseEntity.ok(userService.getUsersWithDept(userId));
	}
	
	public ResponseEntity<Object> getUserWithDeptFallBack(Exception exception) {
		return ResponseEntity.ok("User service is experiencing an outage"); 
	}
}
