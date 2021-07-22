package com.spring.my.app.api.service;

import com.spring.my.app.api.model.Sample;
import com.spring.my.app.api.repository.SampleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@Service
public class SampleServiceImpl implements SampleService{
    @Autowired
    private SampleRepository sampleRepository;

    public List<Sample> getSampleList(){
        List<Sample> sampleList = sampleRepository.findAll();
        return sampleList;
    }

    public Sample addSample(@RequestBody Sample sample){
        Sample sample1 = sampleRepository.save(sample);
        log.info("sample insert! : " + sample1.toString());
        return sample1;

    }
    public List<Sample> getSampleByName(String name){
        List<Sample> sampleList = sampleRepository.findByLastname(name);
        return sampleList;
    }
}
