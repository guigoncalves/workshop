package com.guilhermesgon.workshop.service;

import com.guilhermesgon.workshop.domain.Post;
import com.guilhermesgon.workshop.repository.PostRepository;
import com.guilhermesgon.workshop.repository.UserRepository;
import com.guilhermesgon.workshop.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository repository;

    public Post findById(String id) {
        Post post = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post nao encontrado"));
        return post;
    }

    public List<Post> findByTitle(String text) {
       return repository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        return repository.fullSearch(text, minDate, maxDate);
    }
}
