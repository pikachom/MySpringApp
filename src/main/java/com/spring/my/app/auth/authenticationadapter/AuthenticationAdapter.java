package com.spring.my.app.auth.authenticationadapter;

import org.springframework.security.core.Authentication;

public interface AuthenticationAdapter {
    //TODO: 접근자 확인
    public boolean supports(Authentication authentication);

    public AdaptedAuthentication adaptAuthentication(Authentication authentication);

}
