package com.maniraj.userPostManagement.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "post_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "comment_content", length = 6000)
    private String commentContent;
    private Long likeCount;
    private List<String> commentReplies;
}
