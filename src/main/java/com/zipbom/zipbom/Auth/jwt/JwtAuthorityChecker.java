package com.zipbom.zipbom.Auth.jwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtAuthorityChecker {
    UserAuthority authority() default UserAuthority.ROLE_ANONYMOUS_USER;
}
