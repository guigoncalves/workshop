package com.guilhermesgon.workshop.service;

import com.guilhermesgon.workshop.domain.User;
import com.guilhermesgon.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }
}
