package com.spring.my.app.auth.authenticationadapter;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;


public class GoogleOAuth2AuthenticationAdapter implements AuthenticationAdapter{
    @Override
    public boolean supports(Authentication authentication) {
        if(authentication.getClass() != OAuth2AuthenticationToken.class)
            return false;
        return ((OAuth2AuthenticationToken)authentication).getAuthorizedClientRegistrationId().equals("google");
    }

    @Override
    public AdaptedAuthentication adaptAuthentication(Authentication authentication) {
        return new AdaptedAuthentication((String)((OAuth2AuthenticationToken)authentication).getPrincipal().getAttributes().get("email"), authentication.getAuthorities());
    }
}
