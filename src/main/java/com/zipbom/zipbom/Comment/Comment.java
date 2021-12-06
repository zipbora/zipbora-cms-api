package com.zipbom.zipbom.Comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Board.model.Board;
//import com.zipbom.zipbom.Board.model.Board;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;
}
