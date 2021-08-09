package com.spring.my.app.auth.oauth2;

import com.spring.my.app.auth.entity.Authority;
import com.spring.my.app.auth.entity.User;
import com.spring.my.app.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

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

        //registrationId, userNameAttributeName, oAuth2User.getAttributes()를 이용해 OAuth2Attribute 생성
        OAuth2Attributes attributes = OAuth2Attributes.
                of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(Authority.USER_ROLE)),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuth2Attributes attributes) {
        User user = userRepository.findOneWithAuthoritiesByUsername(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
