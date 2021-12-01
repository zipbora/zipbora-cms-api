package com.zipbom.zipbom.Auth.model;

import com.zipbom.zipbom.Comment.Comments;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Comments comments;
}
