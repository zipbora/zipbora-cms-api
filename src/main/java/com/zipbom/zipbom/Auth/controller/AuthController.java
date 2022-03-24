package com.zipbom.zipbom.Auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zipbom.zipbom.Auth.dto.CheckEmailDuplicateDto;
import com.zipbom.zipbom.Auth.dto.LoginDto;
import com.zipbom.zipbom.Auth.dto.SignUpRequestDto;
import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.Auth.service.AuthService;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Util.response.CMRespDto;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;

import io.swagger.annotations.ApiOperation;
import javassist.bytecode.DuplicateMemberException;

@RestController
public class AuthController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	public AuthService authService;
	@Autowired
	private KakaoAPI kakao;

	@PostMapping("/checkEmailDuplicate")
	@ApiOperation(value = "이메일 중복 체크")
	public CMRespDto<?> checkEmailDuplicate(@RequestBody CheckEmailDuplicateDto checkEmailDuplicateDto) {
		return authService.checkEmailDuplicate(checkEmailDuplicateDto);
	}

	@PostMapping("/signUp")
	@ApiOperation(value = "유저로 회원 가입")
	@JwtAuthorityChecker(authority = UserAuthority.ROLE_ANONYMOUS_USER)
	public SuccessResponseDto<?> signUp(HttpServletRequest httpServletRequest,
		@RequestBody SignUpRequestDto signUpRequestDto) throws DuplicateMemberException {
		return authService.signUp(httpServletRequest, signUpRequestDto);
	}

	@PostMapping(value = "/login")
	@ApiOperation(value = "로그인")
	public SuccessResponseDto<?> login(@RequestBody LoginDto loginDto) {
		return authService.login(loginDto);
	}
}
