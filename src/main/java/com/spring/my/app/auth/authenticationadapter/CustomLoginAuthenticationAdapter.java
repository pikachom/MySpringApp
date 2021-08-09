package com.spring.my.app.auth.authenticationadapter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.stream.Collectors;
//자체로
public class CustomLoginAuthenticationAdapter implements AuthenticationAdapter{

    @Override
    public boolean supports(Authentication authentication) {
        return authentication.getClass() == UsernamePasswordAuthenticationToken.class;
    }

    @Override
    public AdaptedAuthentication adaptAuthentication(Authentication authentication) {
        return new AdaptedAuthentication(authentication.getName(), authentication.getAuthorities());
    }

}
