package com.spring.my.app.auth;

import com.spring.my.app.user.Role;
import com.spring.my.app.user.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;

@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@ToString
public class UserDetailsImpl implements UserDetails {

    private String username;
    private String password;

    public static UserDetails toUserDetails(User user){
        return UserDetailsImpl.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder //빌더패턴으로 생성자를 생성해줌
    public UserDetailsImpl(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
