package com.zipbom.zipbom.Auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipbom.zipbom.Auth.dto.JwtGetUserInfoResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Slf4j
@Component
public class JwtServiceImpl {
    private final String secretKey = "zipbom";

    public String createToken(JwtGetUserInfoResponseDto jwtGetUserInfoResponseDto) {
        long tokenExpireTime = 1000L * 60 * 60;
        System.out.println("----------------");
        System.out.println(jwtGetUserInfoResponseDto.getRole());
        System.out.println("----------------");
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject("userToken")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpireTime))
                .claim("user", jwtGetUserInfoResponseDto)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();
    }

    public Map<String, Object> getInfo(String token) {
        Jws<Claims> claims;
        try {
            claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token); // secretKey를 사용하여 복호화
        } catch (Exception e) {
            return null;
        }

        return claims.getBody();
    }

    public String getUserId(String token) {
        JwtGetUserInfoResponseDto jwtGetUserInfoResponseDto = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jwtGetUserInfoResponseDto = mapper.convertValue(
                    getInfo(token).get("user")
                    , JwtGetUserInfoResponseDto.class);
        } catch (Exception e) {
            return null;
        }
        return jwtGetUserInfoResponseDto.getUserId();
    }

    public void checkValid(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token);
        } catch (Exception e) {
            log.info("{}", e.toString());
            throw new IllegalArgumentException("jwt error");
        }
    }
}