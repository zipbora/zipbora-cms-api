package com.zipbom.zipbom.Auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthority {
    ROLE_ANONYMOUS_USER("ROLE_ANONYMOUS_USER", "익명 유저"),
    ROLE_USER("ROLE_USER", "유저"),
    ROLE_REAL_ESTATE_AGENT("ROLE_REAL_ESTATE_AGENT", "중개사");

    private final String key;
    private final String title;

}
