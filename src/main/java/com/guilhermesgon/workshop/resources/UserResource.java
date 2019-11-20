package com.guilhermesgon.workshop.resources;

import com.guilhermesgon.workshop.domain.User;
import com.guilhermesgon.workshop.dto.UserDto;
import com.guilhermesgon.workshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable String id) {
        UserDto user = new UserDto(service.findById(id));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDto user) {
        User obj = service.fromDto(user);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDto dto) {
        User user = service.fromDto(dto);
        user.setId(id);
        service.update(user);
        return ResponseEntity.noContent().build();
    }

}
