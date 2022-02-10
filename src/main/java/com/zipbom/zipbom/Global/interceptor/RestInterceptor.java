package com.zipbom.zipbom.Global.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.Auth.dto.JwtGetUserInfoResponseDto;
import com.zipbom.zipbom.Auth.jwt.JwtAuthorityChecker;
import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Global.exception.BusinessException;
import com.zipbom.zipbom.Global.exception.error.ErrorCode;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtServiceImpl jwtService;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {

        if (activeProfile.equals("dev") || activeProfile.equals("local")) {
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        JwtAuthorityChecker functionAuthority = handlerMethod.getMethodAnnotation(JwtAuthorityChecker.class);

        if (functionAuthority == null) {
            return true;
        }

        String token = request.getHeader("jwt-auth-token");

        if (token != null && token.length() > 0) {
            jwtService.checkValid(token);
            ObjectMapper mapper = new ObjectMapper();
            if (jwtService.getInfo(token).get("User") != null) {
                JwtGetUserInfoResponseDto jwtGetUserInfoResponseDto = mapper.convertValue(jwtService.getInfo(token).get("User"), JwtGetUserInfoResponseDto.class);
                if (functionAuthority.authority().equals(jwtGetUserInfoResponseDto.getRole())) {
                    return true;
                }
            }
        }

        throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
    }
}