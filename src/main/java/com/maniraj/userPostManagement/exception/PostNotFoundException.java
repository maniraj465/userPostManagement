package com.maniraj.userPostManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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
        this("Post not found with userId " +  userId + " and postId: " + postId);
    }
}
