package com.spring.my.app.auth.authenticationadapter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
//Authentication 객체는 OAuth provider마다 양식이 조금 다르다.
//양식이 다르면 Token을 발급할 때 각 양식별로 Token 발급 방식이 필요하다.
//하나의 방법으로 Token을 발급할 수 있도록 통일한 객체이다.
//자체 로그인의 Authentication객체도 AdaptedAuthentication으로 통일한다.
public class AdaptedAuthentication {
    private final String name;
    private final Collection<? extends GrantedAuthority> authorities;
}
