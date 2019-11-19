package com.guilhermesgon.workshop.config;

import com.guilhermesgon.workshop.domain.User;
import com.guilhermesgon.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();

        List<User> users = Arrays.asList(
                new User("1", "Maria", "maria@email.com"),
                new User("2", "Fernando", "fernando@email.com"),
                new User("3", "Lucas", "lucas@email.com")
        );

       userRepository.saveAll(users);
    }
}
