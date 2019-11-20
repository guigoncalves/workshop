package com.guilhermesgon.workshop.config;

import com.guilhermesgon.workshop.domain.Post;
import com.guilhermesgon.workshop.domain.User;
import com.guilhermesgon.workshop.repository.PostRepository;
import com.guilhermesgon.workshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();

        List<User> users = Arrays.asList(
                new User(null, "Maria", "maria@email.com"),
                new User(null, "Fernando", "fernando@email.com"),
                new User(null, "Lucas", "lucas@email.com")
        );

        List<Post> posts = Arrays.asList(
                new Post(null, sdf.parse("20/03/2019"), "Partiu Viagem!", "Vou viajar para Sao Paulo", users.get(0)),
                new Post(null, sdf.parse("10/01/2019"), "Acordei Feeeliiiz!", "Vou viajar para Sao Paulo", users.get(0))
        );

        postRepository.saveAll(posts);
        userRepository.saveAll(users);
    }
}
