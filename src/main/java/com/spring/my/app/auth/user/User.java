package com.spring.my.app.auth.user;

import lombok.*;

import javax.persistence.*;

@Getter
@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Entity
@ToString
public class User {

    @Id //PK임을 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 값을 자동으로 만듦
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    //EnumType.ORDINAL : enum 순서 값을 DB에 저장
    //EnumType.STRING : enum 이름을 DB에 저장
    @Column(nullable = false)
    private Role role;

    @Builder //빌더패턴으로 생성자를 생성해줌
    public User(String name, String email, String password, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}