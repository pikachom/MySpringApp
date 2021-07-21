package com.spring.my.app.api.controller;

import com.spring.my.app.api.model.Sample;
import com.spring.my.app.api.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sample")
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @GetMapping
    List<Sample> getSamples(){
        log.info("get all sample method called");
        return sampleService.getSampleList();
    }
    @GetMapping("/{lastName}")
    List<Sample> getSampleByLastName(@PathVariable String lastName){
        log.info("find sample by lastName : {}", lastName);
        return sampleService.getSampleByName(lastName);
    }
    @PostMapping
    Sample postSample(@RequestBody Sample sample){
        log.info("inserting {}", sample);
        sampleService.addSample(sample);
        return sample;
    }
//    @PostMapping
//    void postSample(){
//        sampleService.insertSample();
//    }
}
