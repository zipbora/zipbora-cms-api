package com.zipbom.zipbom.Auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class JwtGetUserInfoResponseDto {
    private String userId;
    private List<?> role;
    private String email;
}
