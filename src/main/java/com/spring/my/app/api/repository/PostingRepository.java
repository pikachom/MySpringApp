package com.spring.my.app.api.repository;

import com.spring.my.app.api.model.Posting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostingRepository extends MongoRepository<Posting, String> {
    Optional<Posting> findById(Long id);
//    List<Posting> findAll();
//    List<Posting> findByAuthor(String name);
}
