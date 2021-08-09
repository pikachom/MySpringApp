package com.spring.my.app.auth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//토큰을 담는 dto
public class TokenDto {
    private String token;
}
