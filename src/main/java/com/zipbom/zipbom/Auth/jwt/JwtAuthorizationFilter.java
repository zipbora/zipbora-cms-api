package com.zipbom.zipbom.Auth.jwt;

import com.zipbom.zipbom.Auth.dto.JwtGetUserInfoResponseDto;
import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private JwtUtil jwtUtil;
    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtil jwtUtil) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header, where the JWT token should be
        String header = request.getHeader("Authorization");

        // If header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        // If header is present, try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String userId = null;
        String token = request.getHeader("Authorization")
                .replace("Bearer ", ""); //Bear 다음에 한칸 뛰어야한다
        if (token != null) {
            try {
                JwtGetUserInfoResponseDto jwtGetUserInfoResponseDto = jwtUtil.getUserInfo(token);
                userId = jwtGetUserInfoResponseDto.getUserId();
            } catch (ExpiredJwtException e) {
                logger.debug("token maybe expired");
            }
            if (userId != null) {
                Optional<User> userOptional = userRepository.findByUserId(userId);
                User user = userOptional.get();
                PrincipalDetails principalDetails = PrincipalDetails.of(user);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
                return authentication;
            } else {
                logger.info("token maybe expired: userUuid is null.");
            }
        }
        return null;
    }
}
