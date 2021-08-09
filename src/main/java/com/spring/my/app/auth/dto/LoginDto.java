package com.spring.my.app.auth.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//로그인할 때 id, pw를 전달하기 위한 dto - dto는 모든 것을 포함하는 dto를 만들기보다는 각 경우마다 따로 만드는 게 좋다는 식으로 어디서 들었는데...
//예: UserDto를 LoginDto의 역할을 하게 사용하지 않는다.
public class LoginDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;

}
