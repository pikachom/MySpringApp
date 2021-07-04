package com.spring.my.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    private String id;
    private String test;
}
