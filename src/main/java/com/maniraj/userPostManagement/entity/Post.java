package com.maniraj.userPostManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_posts")
// @JsonIgnoreProperties({"userId", "post_content"}) This class level annotation is used to ignore list of fields in the response - static filtering
// @JsonFilter annotation is for dynamic filtering.
//@JsonFilter("PostBeanPostIdFilter")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "user_id")
    // Change field name in the response - Serialization using Jackson library
    // @JsonProperty("user_id")
    private Long userId;
    // This field level annotation is used to ignore particular field in the response (ex:- Password field) - static filtering
    // @JsonIgnore
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
