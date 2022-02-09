package com.zipbom.zipbom.Auth.controller;

import com.zipbom.zipbom.Auth.dto.LoginDto;
import com.zipbom.zipbom.Auth.dto.CheckEmailDuplicateDto;
import com.zipbom.zipbom.Auth.dto.SignUpRequestDto;
import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.Auth.service.AuthService;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/anonymous-user")
    @JwtAuthorityChecker(authority = UserAuthority.ROLE_ANONYMOUS_USER)
    public String checkAnonymousUser() {
        return "Hello AnonymousUser";
    }

    @GetMapping("/real-estate-agent")
    @JwtAuthorityChecker(authority = UserAuthority.ROLE_REAL_ESTATE_AGENT)
    public String checkRealEstateAgent() {
        return "Hello RealEstateAgent";
    }

    @GetMapping("/user")
    @JwtAuthorityChecker(authority = UserAuthority.ROLE_USER)
    public String checkUser() {
        return "Hello user";
    }

    @PostMapping("/checkEmailDuplicate")
    public CMRespDto<?> checkEmailDuplicate(@RequestBody CheckEmailDuplicateDto checkEmailDuplicateDto) {
        return authService.checkEmailDuplicate(checkEmailDuplicateDto);
    }

    @PostMapping("/signUp")
    public CMRespDto<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return authService.signUp(signUpRequestDto);
    }

    @PostMapping(value = "/login")
    public CMRespDto<?> login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
