package com.zipbom.zipbom.Auth.controller;

import com.zipbom.zipbom.Auth.dto.AccessTokenDto;
import com.zipbom.zipbom.Auth.dto.CMRespDto;
import com.zipbom.zipbom.Auth.dto.CheckEmailDuplicateDto;
import com.zipbom.zipbom.Auth.dto.SignUpRequestDto;
import com.zipbom.zipbom.Auth.service.AuthService;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KakaoAPI kakao;

    @Autowired
    public AuthService authService;

    @ApiOperation(value = "roleTest", notes = "roleTest")
    @GetMapping("/master")
    public String masterJsonReturnTest() {
        return "Hello Master";
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
    public CMRespDto<?> login(@RequestBody AccessTokenDto accessTokenDto) {
        HashMap<String, Object> userInfo = kakao.getUserInfo(accessTokenDto.getAccessToken());
        return authService.login((String) userInfo.get("id"));
    }
}
