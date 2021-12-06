package com.zipbom.zipbom.Auth.jwt;

import com.zipbom.zipbom.Auth.dto.JwtGetUserInfoResponseDto;
import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class JwtUtil {
    private static long JWT_ACCESS_TOKEN_VALIDITY = 6000; // 10분
    private static String secret = "zipbom";

    public String generateAccessToken(PrincipalDetails principalDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority role : principalDetails.getAuthorities()) {
            roles.add(role.getAuthority());
        }
        claims.put("userId", principalDetails.getUserId());
        claims.put("role", roles);
        return Jwts.builder().setClaims(claims).setSubject(principalDetails.getUserId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_ACCESS_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public JwtGetUserInfoResponseDto getUserInfo(String token) {
        Claims parseInfo = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Map<String, Object> result = new HashMap<>();
        JwtGetUserInfoResponseDto jwtGetUserInfoResponseDto =
                JwtGetUserInfoResponseDto.builder()
                .userId((String) parseInfo.get("userId"))
                .build();
        return jwtGetUserInfoResponseDto;
    }
}
