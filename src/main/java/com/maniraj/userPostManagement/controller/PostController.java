package com.maniraj.userPostManagement.controller;

import com.maniraj.userPostManagement.entity.Post;
import com.maniraj.userPostManagement.service.PostServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;

@RestController
@RequestMapping(path = "/users")
public class PostController {

    PostServiceImpl service;
    MessageSource messageSource;

    public PostController(PostServiceImpl service, MessageSource messageSource) {
        this.messageSource = messageSource;
        this.service = service;
    }

    @GetMapping(path = "greet")
    public String greeting() {
        Locale local = LocaleContextHolder.getLocale();
        return messageSource.getMessage("hello.message", null, "Hello from Default!", local);
    }

    @PostMapping(path = "{userId}/posts", consumes = { "multipart/form-data" })
    public ResponseEntity<?> createPost(@RequestPart("post") Post post, @RequestPart("post_picture") MultipartFile post_picture, @PathVariable Long userId) throws IOException {
        service.addPost(post, post_picture, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping(path = "{userId}/posts/{postId}", consumes = { "multipart/form-data" })
    public ResponseEntity<?> updatePost(@RequestPart("post") Post post, @RequestPart("post_picture") MultipartFile post_picture, @PathVariable Long userId, @PathVariable Long postId) throws IOException {
        service.updatePost(post, post_picture, userId, postId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping(path = "{userId}/posts/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long userId, @PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPost(userId, postId));
    }

    @GetMapping(path = "{userId}/posts")
    public ResponseEntity<?> getAllPosts(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllPosts(userId));
    }

    @DeleteMapping(path = "{userId}/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        service.deletePost(userId, postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
