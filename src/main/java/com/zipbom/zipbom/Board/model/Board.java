package com.zipbom.zipbom.Board.model;

import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Comment.Comments;

import javax.persistence.*;

@Entity
@Table(name = "BOARD")
public class Board {

    @Id
    @Column(name = "BOARD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Comments comments;

    @ManyToOne
    private User user;
}
