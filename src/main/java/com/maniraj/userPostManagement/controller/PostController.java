package com.maniraj.userPostManagement.controller;

import com.maniraj.userPostManagement.entity.Post;
import com.maniraj.userPostManagement.exception.PostNotFoundException;
import com.maniraj.userPostManagement.service.PostServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

//    @GetMapping(path = "{userId}/posts/{postId}")
//    public ResponseEntity<?> getPost(@PathVariable Long userId, @PathVariable Long postId) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.getPost(userId, postId));
//    }


    // Added links to perform subsequent operations
    @GetMapping(path = "{userId}/posts/{postId}")
    public EntityModel<Optional<Post>> getPost(@PathVariable Long userId, @PathVariable Long postId) {
        Optional<Post> post = service.getPost(userId, postId);
        if(post.isPresent()) {
            // Added links to perform subsequent operations
            EntityModel<Optional<Post>> postEntityModel = EntityModel.of(post);
            WebMvcLinkBuilder getAllPostsLink = linkTo(methodOn(this.getClass()).getAllPosts(userId));
            postEntityModel.add(getAllPostsLink.withRel("all-posts"));
            WebMvcLinkBuilder deletePostLink = linkTo(methodOn(this.getClass()).deletePost(userId, postId));
            postEntityModel.add(deletePostLink.withRel("delete-post"));
            return postEntityModel;
        }
        else {
            throw new PostNotFoundException(userId, postId);
        }
    }

    @GetMapping(path = "{userId}/posts")
    public ResponseEntity<?> getAllPosts(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllPosts(userId));
    }

    // Added dynamic filter to ignore postId field - Not Working
//    @GetMapping(path = "{userId}/posts")
//    public MappingJacksonValue getAllPosts(@PathVariable Long userId) {
//        List<Post> posts = service.getAllPosts(userId);
//
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(posts);
//        SimpleBeanPropertyFilter postBeanFilter = SimpleBeanPropertyFilter.filterOutAllExcept("userId", "postContent", "postImage", "hashTags", "likedUsers", "comments");
//        FilterProvider filters = new SimpleFilterProvider().addFilter("PostBeanPostIdFilter", postBeanFilter);
//        mappingJacksonValue.setFilters(filters);
//
//        return mappingJacksonValue;
//    }

    @DeleteMapping(path = "{userId}/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long userId, @PathVariable Long postId) {
        service.deletePost(userId, postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
