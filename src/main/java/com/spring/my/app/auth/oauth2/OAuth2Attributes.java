package com.spring.my.app.auth.oauth2;

import com.spring.my.app.entity.Authority;
import com.spring.my.app.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class OAuth2Attributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuth2Attributes(Map<String, Object> attributes,
                            String nameAttributeKey, String name,
                            String email) {
        this.attributes = attributes;
        this.nameAttributeKey= nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuth2Attributes of(String registrationId,
                                      String userNameAttributeName,
                                      Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuth2Attributes ofGoogle(String userNameAttributeName,
                                             Map<String, Object> attributes) {
        return OAuth2Attributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){

        Authority authority = Authority.builder()
                .authorityName(Authority.USER_ROLE)
                .build();

        return User.builder()
                .username(email)
                .nickname(name)
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
    }

}
