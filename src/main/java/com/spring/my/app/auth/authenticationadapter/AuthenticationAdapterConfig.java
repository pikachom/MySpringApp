package com.spring.my.app.auth.authenticationadapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthenticationAdapterConfig {

    //AuthenticationAdapterFinder에 adapterList을 생성자로 주입
    @Bean
    public AuthenticationAdapterFinder authenticationAdapterFinder(){
        List<AuthenticationAdapter> authenticationAdapterList = new ArrayList<>();
        authenticationAdapterList.add(customLoginAuthenticationAdapter());
        authenticationAdapterList.add(googleOAuth2AuthenticationAdapter());
        return new AuthenticationAdapterFinder(authenticationAdapterList);
    }

    @Bean
    public AuthenticationAdapter customLoginAuthenticationAdapter() {
        return new CustomLoginAuthenticationAdapter();
    }

    @Bean
    public AuthenticationAdapter googleOAuth2AuthenticationAdapter() {
        return new GoogleOAuth2AuthenticationAdapter();
    }

}
