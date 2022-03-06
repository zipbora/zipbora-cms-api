package com.zipbom.zipbom.Auth.controller;

import com.zipbom.zipbom.Auth.dto.LoginDto;
import com.zipbom.zipbom.Auth.dto.CheckEmailDuplicateDto;
import com.zipbom.zipbom.Auth.dto.SignUpRequestDto;
import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.Auth.service.AuthService;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Util.response.CMRespDto;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation(value = "회원 가입")
    public CMRespDto<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return authService.signUp(signUpRequestDto);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "로그인")
    public SuccessResponseDto<?> login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
