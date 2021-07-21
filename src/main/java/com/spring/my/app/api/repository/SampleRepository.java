package com.spring.my.app.api.repository;

import com.spring.my.app.api.model.Sample;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface SampleRepository extends MongoRepository<Sample, String> {
    List<Sample> findAll();
    List<Sample> findByLastname(String name);
//    public List<Sample> findAll();
//    public Sample insert(Sample sample);
}
