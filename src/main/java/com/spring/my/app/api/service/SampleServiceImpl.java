package com.spring.my.app.api.service;

import com.spring.my.app.api.document.Sample;
import com.spring.my.app.api.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
