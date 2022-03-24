package com.zipbom.zipbom.Auth.service;

import com.zipbom.zipbom.Auth.dto.*;
import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Global.exception.AccessDeniedException;
import com.zipbom.zipbom.Global.exception.ErrorCode;
import com.zipbom.zipbom.Util.response.CMRespDto;
import com.zipbom.zipbom.Util.response.SuccessResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;

import javassist.bytecode.DuplicateMemberException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KakaoAPI kakao;

    @Autowired
    private JwtServiceImpl jwtService;

    public SuccessResponseDto<?> login(LoginDto loginDto) {
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
        return new SuccessResponseDto<>(true, loginResponseDTO);
    }

    private UserAuthority checkAuthority(UserAuthority userAuthority) {
        if (userAuthority == null) {
            throw new AccessDeniedException(ErrorCode.LOGIN_AUTHORITY_NULL);
        }
        return userAuthority;
    }

    @Transactional
    public SuccessResponseDto<?> signUp(HttpServletRequest httpServletRequest, SignUpRequestDto signUpRequestDto) throws DuplicateMemberException {
        String email = signUpRequestDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateMemberException("중복 회원이 존재합니다");
        }
        User user = userRepository.findByUserId(jwtService.getUserId(httpServletRequest.getHeader("jwt-auth-token")))
            .orElseThrow(IllegalArgumentException::new);

        user.setEmail(email);
        user.setNickname(signUpRequestDto.getNickname());
        user.setUserAuthority(UserAuthority.ROLE_USER);
        user.setImageEncoding(signUpRequestDto.getImageEncoding());

        String jwtToken = jwtService.createToken(new JwtGetUserInfoResponseDto(user));

        return new SuccessResponseDto(true, new SignUpDto(jwtToken));
    }

    public CMRespDto<?> checkEmailDuplicate(CheckEmailDuplicateDto checkEmailDuplicateDto) {
        String email = checkEmailDuplicateDto.getEmail();
        if (userRepository.existsByEmail(email)) {
            return new CMRespDto<>(201, "중복 회원 존재", email);
        }
        return new CMRespDto<>(200, "회원가입 가능", email);
    }
}
