package com.zipbom.zipbom.Auth.service;

import com.zipbom.zipbom.Auth.dto.CMRespDto;
import com.zipbom.zipbom.Auth.dto.LoginRequestDto;
import com.zipbom.zipbom.Auth.dto.LoginResponseDto;
import com.zipbom.zipbom.Auth.jwt.JwtAuthorizationFilter;
import com.zipbom.zipbom.Auth.jwt.JwtUtil;
import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Transactional
    public CMRespDto<?> login(LoginRequestDto loginRequestDTO) {
        String userId = loginRequestDTO.getUserId();
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            PrincipalDetails principalDetails = PrincipalDetails.of(user);
            String jwtToken = jwtUtil.generateAccessToken(principalDetails);
            LoginResponseDto loginResponseDTO = LoginResponseDto.builder()
                    .jwtToken(jwtToken)
                    .build();
            return new CMRespDto<>(200, "jwt 반환", loginResponseDTO);
        }
        return new CMRespDto<>(201, "회원 가입이 안된 유저", null);
    }
}
