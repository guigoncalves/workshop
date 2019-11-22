package com.guilhermesgon.workshop.resources;

import com.guilhermesgon.workshop.domain.Post;
import com.guilhermesgon.workshop.resources.util.URL;
import com.guilhermesgon.workshop.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value="/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value="/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(text);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value="/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value="text", defaultValue = "") String text,
            @RequestParam(value="minDate", defaultValue = "") String minDateString,
            @RequestParam(value="maxDate", defaultValue = "") String maxDateString) {
        text = URL.decodeParam(text);
        Date minDate = URL.convertDate(minDateString, new Date(0L)); //first date in case of fail
        Date maxDate = URL.convertDate(maxDateString, new Date()); // current date in case of fail
        List<Post> posts = service.fullSearch(text, minDate, maxDate);
        return ResponseEntity.ok().body(posts);
    }
}
