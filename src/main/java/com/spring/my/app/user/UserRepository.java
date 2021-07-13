package com.spring.my.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { //JpaRepository<엔티티클래스, PK 클래스>
    // 기본 메소드 참조: https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
    // 추가 메소드 만드는 방법
    // findBy*: *을 기준으로 찾기
    // countBy*: *의 개수 찾기
    // 조건 관련 키워드 예시
    // And      findByEmailAndUserId(String email, String userId)       여러필드를 and 로 검색
    // Or       findByEmailOrUserId(String email, String userId)        여러필드를 or 로 검색
    // pageable: pageable의 구현클래스의 객체를 인자로 전달하면 Page<엔티티 클래스>로 결과를 리턴받을 수 있다.
    Optional<User> findByEmail(String email);
    Optional<User> findByPassword(String password);
}
