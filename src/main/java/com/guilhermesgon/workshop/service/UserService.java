package com.guilhermesgon.workshop.service;

import com.guilhermesgon.workshop.domain.User;
import com.guilhermesgon.workshop.dto.UserDto;
import com.guilhermesgon.workshop.repository.UserRepository;
import com.guilhermesgon.workshop.service.exception.ObjectNotFoundException;
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

    public User findById(String id) {
        User user = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário nao encontrado"));
        return user;
    }

    public User insert(User user) {
        return repository.insert(user);
    }

    public User fromDto(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return user;
    }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public void update(User user) {
        User newUser = findById(user.getId());
        updateData(newUser, user);
        repository.save(newUser);
    }

    private void updateData(User toUser, User fromUser) {
        toUser.setName(fromUser.getName());
        toUser.setEmail(fromUser.getEmail());
    }
}
