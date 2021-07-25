package com.connectus.platform.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Document
@NoArgsConstructor
public class User {
	@Id private String id;	
	@NonNull private String firstName;
	private String lastName;
}
