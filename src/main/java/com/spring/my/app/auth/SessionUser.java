package com.spring.my.app.auth;

import com.spring.my.app.user.User;
import lombok.Getter;

import java.io.Serializable;

// 세션에 저장하기 위해 Serializable 구현
// User 클래스(@Entity)에 Serializable을 구현하면 다른 엔티티와 관계가 형성됐을 때 해당 엔티티도 Serializable을 구현해야 할 수 있기 때문에 성능상 문제가 발생할 수 있음
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

