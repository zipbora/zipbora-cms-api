package com.zipbom.zipbom.Auth.model;

//import com.zipbom.zipbom.Board.model.Board;
import com.zipbom.zipbom.Board.model.Boards;
import com.zipbom.zipbom.Comment.Comments;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Boards boards;

}
