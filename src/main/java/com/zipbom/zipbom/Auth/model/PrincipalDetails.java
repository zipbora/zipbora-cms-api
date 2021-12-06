package com.zipbom.zipbom.Auth.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PrincipalDetails implements UserDetails {
    private final String userId;
    private final Collection<? extends GrantedAuthority> authorities;
    private String email;

    private PrincipalDetails(String userId,String email, Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
        this.userId = userId;
        this.email = email;
    }
    public static PrincipalDetails of(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new PrincipalDetails(
                user.getId(),
                user.getEmail(),
                authorities
        );
    }
    public String getUserId() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
