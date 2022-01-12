package com.zipbom.zipbom.Auth.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
}
