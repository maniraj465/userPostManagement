package com.maniraj.userPostManagement.repository;

import com.maniraj.userPostManagement.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> getPostByUserIdAndPostId(Long userId, Long postId);

    Optional<List<Post>> getPostByUserId(Long userId);

    void deletePostByUserIdAndPostId(Long userId, Long postId);
}
