package com.connectus.platform.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.connectus.platform.models.User;
@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {

}
