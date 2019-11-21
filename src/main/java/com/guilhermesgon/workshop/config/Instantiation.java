package com.guilhermesgon.workshop.config;

import com.guilhermesgon.workshop.domain.Post;
import com.guilhermesgon.workshop.domain.User;
import com.guilhermesgon.workshop.dto.AuthorDto;
import com.guilhermesgon.workshop.dto.CommentDto;
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
        postRepository.deleteAll();

        List<User> users = Arrays.asList(
                new User(null, "Maria", "maria@email.com"),
                new User(null, "Fernando", "fernando@email.com"),
                new User(null, "Lucas", "lucas@email.com")
        );

        userRepository.saveAll(users);

        List<Post> posts = Arrays.asList(
                new Post(null, sdf.parse("20/03/2019"), "Partiu Viagem!", "Vou viajar para Sao Paulo", new AuthorDto(users.get(0))),
                new Post(null, sdf.parse("10/01/2019"), "Acordei Feeeliiiz!", "Vou viajar para Sao Paulo", new AuthorDto(users.get(0)))
        );

        CommentDto cm1 = new CommentDto("Otima Viagem!", sdf.parse("21/03/2019"), new AuthorDto(users.get(1)));
        CommentDto cm2 = new CommentDto("Tambem estou feliz", sdf.parse("21/03/2019"), new AuthorDto(users.get(2)));

        posts.get(0).setComments(Arrays.asList(cm1));
        posts.get(1).setComments(Arrays.asList(cm2));

        postRepository.saveAll(posts);

        users.get(0).setPosts(posts);
        userRepository.save(users.get(0));
    }
}
