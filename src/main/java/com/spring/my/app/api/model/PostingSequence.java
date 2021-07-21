package com.spring.my.app.api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("posting_sequence")
public class PostingSequence {
    @Id
    private String id;
    private Long seq;
}
