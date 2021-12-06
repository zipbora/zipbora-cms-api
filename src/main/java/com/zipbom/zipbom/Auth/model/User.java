package com.zipbom.zipbom.Auth.model;

import com.zipbom.zipbom.Auth.enums.Role;
import com.zipbom.zipbom.Board.model.Boards;
import lombok.Builder;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    private String email;

    @Embedded
    private Boards boards;

    @Enumerated(EnumType.STRING)
    private Role role;
}
