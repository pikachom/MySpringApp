package com.spring.my.app.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority {

    public static final String USER_ROLE = "ROLE_USER";

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}
