package com.zipbom.zipbom.Auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginResponseDto {
    private String jwtToken;
}
