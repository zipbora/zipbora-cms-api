package com.zipbom.zipbom.Board.model;


import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Comment.Comments;

import javax.persistence.*;

@Entity
@Table(name = "board")
public class Board {
    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Embedded
    private Comments comments;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
