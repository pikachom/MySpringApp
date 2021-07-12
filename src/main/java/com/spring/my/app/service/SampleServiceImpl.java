package com.spring.my.app.service;

import com.spring.my.app.document.Sample;
import com.spring.my.app.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleServiceImpl implements SampleService{
    @Autowired
    private SampleRepository sampleRepository;

    public List<Sample> getSampleList(){
        List<Sample> sampleList = sampleRepository.findAll();
        return sampleList;
    }
}
