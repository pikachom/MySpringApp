package com.spring.my.app.auth;

import com.spring.my.app.user.Role;
import com.spring.my.app.user.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Data
public class RegistrationForm {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String picture;
    private Role role;

    public User toUser(PasswordEncoder passwordEncoder) {
        System.out.println(passwordEncoder.encode(password));
        return User.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .picture(picture)
                .role(Role.USER)
                .build();
    }
}