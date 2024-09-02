package com.maniraj.userPostManagement.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        this("Post not found.");
    }

    public PostNotFoundException(String message) {
        super(message);
    }

    public PostNotFoundException(Long userId) {
        this("Post not found for userId : " + userId);
    }

    public PostNotFoundException(Long userId, Long postId) {
        this("Post not found with userId " +  userId + " and postId : " + postId);
    }
}
