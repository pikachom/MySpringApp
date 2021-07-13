package com.spring.my.app.api.controller;

import com.spring.my.app.api.document.Sample;
import com.spring.my.app.api.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @Autowired
    private SampleService sampleService;

    @GetMapping("/")
    List<Sample> getSamples(){
        System.out.println("GET 메소드가 호출되었습니다.");
        return sampleService.getSampleList();
    }
//    @PostMapping
//    void postSample(){
//        sampleService.insertSample();
//    }
}
