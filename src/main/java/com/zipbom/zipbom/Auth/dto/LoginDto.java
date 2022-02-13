package com.zipbom.zipbom.Auth.dto;

import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String accessToken;
    private UserAuthority userAuthority;
}
