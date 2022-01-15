package com.zipbom.zipbom.Auth.service;

import com.zipbom.zipbom.Auth.dto.CheckEmailDuplicateDto;
import com.zipbom.zipbom.Auth.dto.LoginResponseDto;
import com.zipbom.zipbom.Auth.dto.SignUpRequestDto;
import com.zipbom.zipbom.Auth.jwt.JwtUtil;
import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.zipbom.zipbom.Auth.model.Role.USER;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public CMRespDto<?> login(String providerId) {
        User user = userRepository.findByProviderId(providerId)
                .orElse(userRepository.save(User.builder().providerId(providerId).build()));
        PrincipalDetails principalDetails = PrincipalDetails.of(user);

        String jwtToken = jwtUtil.generateAccessToken(principalDetails);
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
                .role(USER)
                .build();
        userRepository.save(user);
        PrincipalDetails principalDetails = PrincipalDetails.of(user);
        String jwtToken = jwtUtil.generateAccessToken(principalDetails);
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
