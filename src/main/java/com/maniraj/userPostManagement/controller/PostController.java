package com.maniraj.userPostManagement.controller;

import com.maniraj.userPostManagement.entity.Post;
import com.maniraj.userPostManagement.service.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class PostController {

    PostServiceImpl service;

    public PostController(PostServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    @RequestMapping(path = "{userId}/posts", consumes = { "multipart/form-data" })
    public ResponseEntity<?> createPost(@RequestPart("post") Post post, @RequestPart("post_picture") MultipartFile image, @PathVariable Long userId) throws IOException {
        service.addPost(post, image, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping
    @RequestMapping(path = "{userId}/posts/{postId}", consumes = { "multipart/form-data" })
    public ResponseEntity<?> updatePost(@RequestPart("post") Post post, @RequestPart("post_picture") MultipartFile image, @PathVariable Long userId, @PathVariable Long postId) throws IOException {
        service.updatePost(post, image, userId, postId);
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
