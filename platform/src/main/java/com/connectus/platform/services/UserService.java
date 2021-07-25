package com.connectus.platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.connectus.platform.models.User;
import com.connectus.platform.repositories.UserMongoRepository;

@Service
public class UserService {

	@Autowired 
	private UserMongoRepository userRepository;

	public User getUserById(String id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
}
