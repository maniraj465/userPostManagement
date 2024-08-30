package com.maniraj.userPostManagement.service;

import com.maniraj.userPostManagement.entity.Post;
import com.maniraj.userPostManagement.exception.PostNotFoundException;
import com.maniraj.userPostManagement.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    PostRepository repository;

    PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Post addPost(Post post, MultipartFile image, Long userId) throws IOException {
        post.setPostImage(image.getBytes());
        post.setUserId(userId);
        return repository.save(post);
    }

    @Override
    public Post updatePost(Post post, MultipartFile image, Long userId, Long postId) throws IOException {
        Optional<Post> existingPost = getPost(userId, postId);
        if (existingPost.isPresent()) {
            post.setPostImage(image.getBytes());
            post.setUserId(userId);
            post.setPostId(postId);
            return repository.save(post);
        }
        else {
            throw new PostNotFoundException(postId);
        }
    }

    @Override
    public Optional<Post> getPost(Long userId, Long postId) {
        Optional<Post> post = repository.getPostByUserIdAndPostId(userId, postId);
        if (post.isEmpty()) {
            throw new PostNotFoundException(userId, postId);
        }
        else {
            return post;
        }
    }

    @Override
    public List<Post> getAllPosts(Long userId) {
        Predicate<? super Post> filterUserPostsPredicate = post -> post.getUserId().equals(userId);
        List<Post> posts = repository.findAll().stream().filter(filterUserPostsPredicate).collect(Collectors.toList());
        if (posts.isEmpty()) {
            throw new PostNotFoundException(userId);
        }
        else {
            return posts;
        }
    }

    @Override
    public void deletePost(Long userId, Long postId) {
        Optional<Post> existingPost = getPost(userId, postId);
        if (existingPost.isPresent()) {
            repository.deletePostByUserIdAndPostId(userId, postId);
        }
        else {
            throw new PostNotFoundException(userId, postId);
        }
    }
}
