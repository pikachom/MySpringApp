package com.spring.my.app.api.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("posting")
public class Posting {

    @Transient
    public static final String SEQUENCE_NAME = "posting_sequence";

    @Id
    private Long _id;
    private String title;
    private String content;
    private String author;
    private String created;

    @Builder
    public Posting(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
