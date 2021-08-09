package com.spring.my.app.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonIgnore //Json 변환시 무시
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 unique 값 생성
    private Long userId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @JsonIgnore
    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    //https://parkhyeokjin.github.io/jpa/2019/10/28/JPA-chap6.html 참조
    @ManyToMany//m:n
    @JoinTable(
            name = "user_authority",//조인할 테이블 명
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},//user의 user_id 컬럼을 매핑테이블에서 user_id으로 참조
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})//authority의 authority_name컬럼을 authority_name으로 참조
    private Set<Authority> authorities;

    //ninkname을 업데이트하는 메소드인데 현재 프로젝트 내에서는 사용되지 않음
    public User update(String nickname){
        this.nickname = nickname;
        return this;
    }
}
