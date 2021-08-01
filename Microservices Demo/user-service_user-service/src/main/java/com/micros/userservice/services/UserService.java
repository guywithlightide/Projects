package com.micros.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micros.userservice.entities.User;
import com.micros.userservice.repositories.UserRepository;
import com.micros.userservice.vos.Department;
import com.micros.userservice.vos.ResponseTemplateVO;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public User findUserById(Long userId) {
		return userRepository.getById(userId);
	}

	public ResponseTemplateVO getUsersWithDept(Long userId) {
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user = userRepository.getByUserId(userId);
		vo.setUser(user);
		vo.setDepartment(restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(),Department.class));
		return vo;
	}

}
