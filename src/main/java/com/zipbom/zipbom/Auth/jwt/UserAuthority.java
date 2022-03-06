package com.zipbom.zipbom.Auth.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserAuthority {
	ROLE_REAL_ESTATE_AGENT("ROLE_REAL_ESTATE_AGENT", "중개사"),
	ROLE_USER("ROLE_USER", "유저"),
	ROLE_ONLY_LOGIN("ROLE_ONLY_LOGIN", "로그인만 한 유저"),
	ROLE_ANONYMOUS_USER("ROLE_ANONYMOUS_USER", "익명 유저");

	private final String key;
	private final String title;

}
