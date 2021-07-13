package com.spring.my.app.api.repository;

import com.spring.my.app.api.document.Sample;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends MongoRepository<Sample, String> {
//    public List<Sample> findAll();
//    public Sample insert(Sample sample);
}
