package com.guilhermesgon.workshop.resources;

import com.guilhermesgon.workshop.domain.User;
import com.guilhermesgon.workshop.dto.UserDto;
import com.guilhermesgon.workshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        List<UserDto> users = service.findAll().stream()
                .map(user -> new UserDto(user))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(users);
    }

}
