package com.spring.my.app.auth;

import com.spring.my.app.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
//                .headers().frameOptions().disable() h2-console 화면을 사용하기 위해 해당 옵션들을 disable
//                .and()
                //"/", "/css/**", "/images/**",
                //                        "/js/**", "/h2-console/**", "/login-own", "/register"
                .authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작점
                .antMatchers("/", "/register", "/login-own").permitAll() //해당 url은 모두가 열람가능
                .antMatchers("/posts")
                .hasRole(Role.USER.name()) // api/v1 이하의 url은 user만 권한이 있음
                .antMatchers("/admin")
                .hasRole(Role.ADMIN.name())
                .anyRequest().authenticated() //나머지는 로그인한 모두에게 보여주기
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/authenticate")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutSuccessUrl("/") //로그아웃하면 /으로 이동
                .and()
                .oauth2Login() // OAuth 2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

}