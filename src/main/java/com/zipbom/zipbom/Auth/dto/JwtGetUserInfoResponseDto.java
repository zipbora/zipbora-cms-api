package com.zipbom.zipbom.Auth.dto;

import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.Auth.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JwtGetUserInfoResponseDto {
    private String userId;
    private List<UserAuthority> role;
    private String email;

    public JwtGetUserInfoResponseDto(User user) {
        this.userId = user.getId();
        this.role = Arrays.asList(user.getUserAuthority());
        this.email = user.getEmail();
    }
}
