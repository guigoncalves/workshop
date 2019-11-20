package com.guilhermesgon.workshop.repository;

import com.guilhermesgon.workshop.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}
