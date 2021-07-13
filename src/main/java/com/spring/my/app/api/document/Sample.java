package com.spring.my.app.api.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sample")
public class Sample {
    @Id
    private String id;
    private String name;
//    private String dateRegistered;

//    public Sample(String id, String name, String dateRegistered){
//        this.id = id;
//        this.name = name;
//        this.dateRegistered = dateRegistered;
//    }
}
