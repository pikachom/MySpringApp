package com.spring.my.app.auth;

import com.spring.my.app.user.User;
import com.spring.my.app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;

// OAuth2UserService<R extends OAuth2UserRequest, U extends OAuth2User>
// 1. 함수형 인터페이스
// 2. OAuth2UserRequest를 이용해서 OAuth2User를 load하는 메소드인 loadUser을 가짐

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // OAuth2UserService: CustomOAuth2UserService를 구현한 기본 클래스
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        // DefaultOAuth2UserService의 loadUser를 이용해 OAuth2User를 load
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // UserRequest에서 ClientRegistration(OAuth2.0 Provider에 Client로서 등록된 Client 관련 정보)에서 Provider의 정보를 가져옴
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        // Provider가 사용자의 식별값이 어떤 attribute에 담아서 보내주는지에 대한 정보 할당 (예: google - sub, naver - id) https://github.com/jojoldu/freelec-springboot2-webservice/issues/64
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

//        log.info("registrationId: {}", userRequest.getClientRegistration().getRegistrationId());
//        log.info("clientId: {}", userRequest.getClientRegistration().getClientId());
//        log.info("redirectUri: {}", userRequest.getClientRegistration().getRedirectUri());
//        log.info("redirectUri: {}", userRequest.getClientRegistration().getProviderDetails());
//        log.info("userInfoEndpoint: {}", userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint());
//        log.info("userNameAttributeName: {}", userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName());


        OAuthAttributes attributes = OAuthAttributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        // DB에서 email(id)로 사용자 정보를 Optional로 읽어오고 있으면 Optional내의 User의 update 메소드를 호출, 없으면 OAuthAttributes 정보를 이용해서 User 객체를 생성
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(),attributes.getPicture()))
                .orElse(attributes.toEntity());
        //User 객체를 JpaRepository의 save 메소드를 이용해서 DB에 저장 https://minkukjo.github.io/framework/2020/07/05/Spring-130/
        return userRepository.save(user);
    }
}