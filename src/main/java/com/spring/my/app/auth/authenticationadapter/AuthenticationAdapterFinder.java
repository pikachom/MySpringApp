package com.spring.my.app.auth.authenticationadapter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import java.util.List;

@RequiredArgsConstructor
public class AuthenticationAdapterFinder {
    private final List<AuthenticationAdapter> adapterList;

    public AuthenticationAdapter findAdapter(Authentication authentication){
        for(AuthenticationAdapter authenticationAdapter: adapterList){
            if(authenticationAdapter.supports(authentication))
                return authenticationAdapter;
        }
        return null;
    }
}
