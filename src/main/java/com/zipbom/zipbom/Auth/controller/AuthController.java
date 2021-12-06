package com.zipbom.zipbom.Auth.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @ApiOperation(value="test", notes="test")
    @GetMapping("/")
    public String jasonReturnTest() {
        return "Hello";
    }
}
