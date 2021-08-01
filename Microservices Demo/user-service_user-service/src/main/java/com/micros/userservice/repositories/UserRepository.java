package com.micros.userservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micros.userservice.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User getByUserId(Long userId);

}
