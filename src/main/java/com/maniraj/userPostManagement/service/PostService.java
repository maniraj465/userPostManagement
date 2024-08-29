package com.maniraj.userPostManagement.service;

import com.maniraj.userPostManagement.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Post addPost(Post post, MultipartFile image, Long userId) throws IOException;

    Post updatePost(Post post, MultipartFile image, Long userId, Long postId) throws IOException;

    Optional<Post> getPost(Long userId, Long postId);

    List<Post> getAllPosts(Long userId);

    void deletePost(Long userId, Long postId);
}
