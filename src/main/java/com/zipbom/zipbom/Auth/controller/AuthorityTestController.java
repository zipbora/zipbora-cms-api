package com.zipbom.zipbom.Auth.controller;

import org.springframework.web.bind.annotation.GetMapping;

import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.UserAuthority;

import io.swagger.annotations.ApiOperation;

public class AuthorityTestController {
	@GetMapping("/anonymous-user")
	@ApiOperation(value = "익명유저 권한 테스트")
	@JwtAuthorityChecker(authority = UserAuthority.ROLE_ANONYMOUS_USER)
	public String checkAnonymousUser() {
		return "Hello AnonymousUser";
	}

	@GetMapping("/real-estate-agent")
	@ApiOperation(value = "중개사 권한 테스트")
	@JwtAuthorityChecker(authority = UserAuthority.ROLE_REAL_ESTATE_AGENT)
	public String checkRealEstateAgent() {
		return "Hello RealEstateAgent";
	}

	@GetMapping("/user")
	@ApiOperation(value = "유저 권한 테스트")
	@JwtAuthorityChecker(authority = UserAuthority.ROLE_USER)
	public String checkUser() {
		return "Hello user";
	}

	@GetMapping
	public String check() {
		return "Hello";
	}
}
