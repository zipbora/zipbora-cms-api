package com.zipbom.zipbom.Auth.dto;

import com.zipbom.zipbom.Auth.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Builder
public class JwtGetUserInfoResponseDto {
    private String userId;
    private List<?> role;
    private String email;

    public JwtGetUserInfoResponseDto(User user) {
        this.userId = user.getId();
        this.role = Arrays.asList(user.getUserAuthority());
        this.email = user.getEmail();
    }
}
