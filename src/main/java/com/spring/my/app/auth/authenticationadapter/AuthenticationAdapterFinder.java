package com.spring.my.app.auth.authenticationadapter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import java.util.List;

@RequiredArgsConstructor
public class AuthenticationAdapterFinder {

    private final List<AuthenticationAdapter> adapterList;

    //adapterList내의 모든 AuthenticationAdapter을 돌면서 authenticationAdapter을 찾아줌
    public AuthenticationAdapter findAdapter(Authentication authentication){
        for(AuthenticationAdapter authenticationAdapter: adapterList){
            if(authenticationAdapter.supports(authentication))
                return authenticationAdapter;
        }
        return null;
    }

}
