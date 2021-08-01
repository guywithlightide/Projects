package com.micros.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

		@GetMapping("/user-service-fallback")
		public String userServiceFallBack() {
			return "User service is taking longer than expected. Please try again later";
		}
		
		@GetMapping("/department-service-fallback")
		public String departmentServiceFallBack() {
			return "Department service is taking longer than expected. Please try again later";
		}
}
