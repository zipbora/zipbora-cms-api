package com.zipbom.zipbom.Auth.controller;

import com.zipbom.zipbom.Auth.service.KakaoAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class OauthTestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KakaoAPI kakao;

    @RequestMapping(value="/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/login-test")
    public String login(@RequestParam("code") String code, HttpSession session) {
        logger.info("code : "+code);
        String access_Token = kakao.getAccessToken(code);
        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
        System.out.println("login Controller : " + userInfo);

        if (userInfo.get("email") != null) {
            session.setAttribute("userId", userInfo.get("email"));
            session.setAttribute("access_Token", access_Token);
        }
        return "index";
    }
}
