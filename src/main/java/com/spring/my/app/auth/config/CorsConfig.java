package com.spring.my.app.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//Cors 관련 설정은 CorsFilter를 SecurityConfig에서 .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)로 필터를 적용하는 방법이 있고
//SecurityConfig에서 corsConfigurationSource빈을 이용할 수 있다.
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);                           //Fetch API 생성자의Request()의 credentials 옵션 사용을 허용
        config.addAllowedOrigin("http://localhost:3000");           //허용하는 Origin
        config.addAllowedHeader("*");                               //허용하는 헤더
        config.addAllowedMethod("*");                               //허용하는 메소드
        source.registerCorsConfiguration("/**", config);    //CorsConfiguration을 UrlBasedCorsConfigurationSource에 등록
        return new CorsFilter(source);                              //UrlBasedCorsConfigurationSource을 인자로 CorsFilter 생성
    }

}
