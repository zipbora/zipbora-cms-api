package com.zipbom.zipbom.Auth.service;

import com.zipbom.zipbom.Auth.dto.*;
import com.zipbom.zipbom.Auth.jwt.JwtAuthorizationFilter;
import com.zipbom.zipbom.Auth.jwt.JwtUtil;
import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.zipbom.zipbom.Auth.enums.Role.USER;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

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

    @Transactional
    public CMRespDto<?> signUp(SignUpRequestDto signUpRequestDto) {
        String email = signUpRequestDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            return new CMRespDto<>(500, "중복 회원 존재", email);
        }
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .email(email)
                .role(USER)
                .build();
        userRepository.save(user);
        return new CMRespDto<>(200, "회원 가입 성공", "회원 가입 성공");
    }

    public CMRespDto<?> checkEmailDuplicate(CheckEmailDuplicateDto checkEmailDuplicateDto) {
        String email = checkEmailDuplicateDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            return new CMRespDto<>(201, "중복 회원 존재", email);
        }
        return new CMRespDto<>(200, "회원가입 가능", email);
    }
}
