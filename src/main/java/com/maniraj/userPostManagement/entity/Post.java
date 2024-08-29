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
@Table(name = "user_posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "post_content", length = 6000)
    private String postContent;
    @Lob
    @Column(name = "post_image", length = 5000)
    private byte[] postImage;
    private List<String> hashTags;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_post_id", referencedColumnName = "post_id")
    private List<LikedUser> likedUsers;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_post_id", referencedColumnName = "post_id")
    private List<Comment> comments;

}
