package com.spring.my.app.auth.authenticationadapter;

import org.springframework.security.core.Authentication;

public interface AuthenticationAdapter {
    //TODO: 접근자 확인
    /*
    AuthenticationAdapter가 authentication을 AdaptedAuthentication으로 변활할 수 있는지를 리턴
     */
    public boolean supports(Authentication authentication);

    /*
    authentication을 AdaptedAuthentication으로 변환하여 리턴
     */
    public AdaptedAuthentication adaptAuthentication(Authentication authentication);

}
