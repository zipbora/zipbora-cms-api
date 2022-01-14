package com.zipbom.zipbom.Auth.controller;

import com.zipbom.zipbom.Auth.dto.AccessTokenDto;
import com.zipbom.zipbom.Auth.dto.CheckEmailDuplicateDto;
import com.zipbom.zipbom.Auth.dto.SignUpRequestDto;
import com.zipbom.zipbom.Auth.service.AuthService;
import com.zipbom.zipbom.Auth.service.KakaoAPI;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AuthController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public AuthService authService;
    @Autowired
    private KakaoAPI kakao;

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
        String access_Token = kakao.getAccessToken(accessTokenDto.getCode());
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        return authService.login((String) userInfo.get("id"));
    }
}
