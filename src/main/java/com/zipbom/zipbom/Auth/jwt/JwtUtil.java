package com.zipbom.zipbom.Auth.jwt;

import com.zipbom.zipbom.Auth.dto.JwtGetUserInfoResponseDto;
import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

@Component
public class JwtUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    private static final long JWT_ACCESS_TOKEN_VALIDITY = 6000; // 10분
    @Value("${jwt.secret}")
    private String secret;

    public String generateAccessToken(PrincipalDetails principalDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority role : principalDetails.getAuthorities()) {
            roles.add(role.getAuthority());
        }
        claims.put("userId", principalDetails.getUserId());
        claims.put("role", roles);
        claims.put("email", principalDetails.getEmail());
        return Jwts.builder().setClaims(claims).setSubject(principalDetails.getUserId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public JwtGetUserInfoResponseDto getUserInfo(String token) {
        Claims parseInfo = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String us = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        JwtGetUserInfoResponseDto jwtGetUserInfoResponseDto =
                JwtGetUserInfoResponseDto.builder()
                        .userId((String) parseInfo.get("userId"))
                        .email((String) parseInfo.get("email"))
                        .role(parseInfo.get("role", List.class))
                        .build();
        return jwtGetUserInfoResponseDto;
    }
}
