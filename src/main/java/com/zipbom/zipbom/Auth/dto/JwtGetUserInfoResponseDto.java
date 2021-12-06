package com.zipbom.zipbom.Auth.dto;

import com.zipbom.zipbom.Auth.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtGetUserInfoResponseDto {
    private String userId;
    private Role role;
}
