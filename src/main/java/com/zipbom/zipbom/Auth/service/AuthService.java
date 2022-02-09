package com.zipbom.zipbom.Auth.service;

import com.zipbom.zipbom.Auth.dto.*;
import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KakaoAPI kakao;

    @Autowired
    private JwtServiceImpl jwtService;

    public CMRespDto<?> login(LoginDto loginDto) {
        String providerId = (String) kakao.getUserInfo(loginDto.getAccessToken()).get("providerId");

        User user = userRepository.findByProviderId(providerId)
                .orElseGet(() -> userRepository.save(User.builder()
                        .providerId(providerId)
                        .id(UUID.randomUUID().toString())
                        .userAuthority(UserAuthority.ROLE_ANONYMOUS_USER)
                        .build()));

        String jwtToken = jwtService.createToken(new JwtGetUserInfoResponseDto(user));

        LoginResponseDto loginResponseDTO = LoginResponseDto.builder()
                .jwtToken(jwtToken)
                .build();
        return new CMRespDto<>(200, "jwt 반환", loginResponseDTO);
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
                .userAuthority(UserAuthority.ROLE_ANONYMOUS_USER)
                .build();
        userRepository.save(user);

        String jwtToken = jwtService.createToken(new JwtGetUserInfoResponseDto(user));
        return new CMRespDto<>(200, "회원 가입 성공", jwtToken);
    }

    public CMRespDto<?> checkEmailDuplicate(CheckEmailDuplicateDto checkEmailDuplicateDto) {
        String email = checkEmailDuplicateDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            return new CMRespDto<>(201, "중복 회원 존재", email);
        }
        return new CMRespDto<>(200, "회원가입 가능", email);
    }
}
