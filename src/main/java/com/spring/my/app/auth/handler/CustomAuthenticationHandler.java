package com.spring.my.app.auth.handler;

import com.spring.my.app.auth.authenticationadapter.AdaptedAuthentication;
import com.spring.my.app.auth.authenticationadapter.AuthenticationAdapter;
import com.spring.my.app.auth.authenticationadapter.AuthenticationAdapterFinder;
import com.spring.my.app.auth.jwt.JwtFilter;
import com.spring.my.app.auth.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final AuthenticationAdapterFinder authenticationAdapterFinder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthenticationAdapter adapter = authenticationAdapterFinder.findAdapter(authentication);
        //TODO: 예외처리 필요
        if(adapter == null){
            return;
        }
        AdaptedAuthentication adaptedAuthentication = adapter.adaptAuthentication(authentication);
        String jwt = tokenProvider.createToken(adaptedAuthentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        if(authentication.getClass() == UsernamePasswordAuthenticationToken.class)
            response.setHeader("Access-Control-Allow-Origin", "*");
        else
            response.sendRedirect("http://localhost:3000/set-token/"+jwt);
    }
}
