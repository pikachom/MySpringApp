package com.spring.my.app.auth.authenticationadapter;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class AdaptedAuthentication {
    private final String name;
    private final Collection<? extends GrantedAuthority> authorities;
}
