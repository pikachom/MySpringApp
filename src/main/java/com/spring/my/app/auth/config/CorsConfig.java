package com.spring.my.app.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
