package com.zipbom.zipbom.Auth.controller;

import com.zipbom.zipbom.Auth.dto.CMRespDto;
import com.zipbom.zipbom.Auth.dto.CheckEmailDuplicateDto;
import com.zipbom.zipbom.Auth.dto.LoginRequestDto;
import com.zipbom.zipbom.Auth.dto.SignUpRequestDto;
import com.zipbom.zipbom.Auth.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    public AuthService authService;

    @ApiOperation(value = "test", notes = "test")
    @GetMapping("/")
    public String jsonReturnTest() {
        return "Hello";
    }

    @ApiOperation(value = "roleTest", notes = "roleTest")
    @GetMapping("/master")
    public String masterJsonReturnTest() {
        return "Hello Master";
    }

    @PostMapping("/login")
    public CMRespDto<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }

    @PostMapping("/checkEmailDuplicate")
    public CMRespDto<?> checkEmailDuplicate(@RequestBody CheckEmailDuplicateDto checkEmailDuplicateDto) {
        return authService.checkEmailDuplicate(checkEmailDuplicateDto);
    }

    @PostMapping("/signUp")
    public CMRespDto<?> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        return authService.signUp(signUpRequestDto);
    }
}
