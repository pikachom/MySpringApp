package com.spring.my.app.api.repository;

import com.spring.my.app.api.model.Posting;
import com.spring.my.app.api.model.Sample;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostingRepository extends MongoRepository<Posting, String> {
//    List<Posting> findAll();
//    List<Posting> findByAuthor(String name);
}
