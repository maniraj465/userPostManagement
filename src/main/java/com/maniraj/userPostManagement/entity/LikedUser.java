package com.maniraj.userPostManagement.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "liked_user_details")
public class LikedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "liked_user_id")
    private Long likedUserId;
    private String firstName;
    private String lastName;
    @Column(name = "reaction")
    private Reaction reaction;
}
