package com.zipbom.zipbom.Auth.service;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PrincipalDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId)
            throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("user가 존재하지 않습니다.");
        }
        User user = userOptional.get();
        return PrincipalDetails.of(user);
    }
}
