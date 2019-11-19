package com.guilhermesgon.workshop.repository;

import com.guilhermesgon.workshop.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
