package com.spring.my.app.controller;

import com.spring.my.app.auth.jwt.JwtFilter;
import com.spring.my.app.auth.jwt.TokenProvider;
import com.spring.my.app.dto.LoginDto;
import com.spring.my.app.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @PostMapping("/signin")
    public ResponseEntity<TokenDto> signIn(@Valid @RequestBody LoginDto loginDto, HttpServletResponse response) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        try{
            authenticationSuccessHandler.onAuthenticationSuccess(null, response, authentication);
        } catch (IOException e ) {

        } catch (ServletException e) {

        }

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/signout")
    public ResponseEntity<TokenDto> signOut(@RequestHeader HttpHeaders httpHeaders) {
        log.info(httpHeaders.get(JwtFilter.AUTHORIZATION_HEADER).toString());
        httpHeaders.remove(JwtFilter.AUTHORIZATION_HEADER);

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

}
