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
//Authentication이 성공한 후 호출되는 핸들러
public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;
    private final AuthenticationAdapterFinder authenticationAdapterFinder;

    //Authentication이 성공한 후 생성된 Authentication의 인스턴스를 이용해 jwt 발급(여기서 authenticationAdapterFinder이 사용)
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //AuthenticationAdapter 찾기
        AuthenticationAdapter adapter = authenticationAdapterFinder.findAdapter(authentication);
        //TODO: 예외처리 필요
        if(adapter == null){
            return;
        }
        //AuthenticationAdapter로 AdaptedAuthentication get
        AdaptedAuthentication adaptedAuthentication = adapter.adaptAuthentication(authentication);
        //jwt 발급
        String jwt = tokenProvider.createToken(adaptedAuthentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        //jwt를 http header의 "Authorization" 필드에 add
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        //Authentication이 OAuth2에 의해 생성되었으면 (자체로그인으로부터 생성된 Authentication은 UsernamePasswordAuthenticationToken임)
        //http://localhost:3000/set-token/"+jwt으로 redirect
        //프론트엔드에서 jwt 값을 catch하기 위해
        if(authentication.getClass() != UsernamePasswordAuthenticationToken.class)
            response.sendRedirect("http://localhost:3000/set-token/"+jwt);
    }
}
