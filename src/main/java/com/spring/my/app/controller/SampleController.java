package com.spring.my.app.controller;

import com.spring.my.app.document.Sample;
import com.spring.my.app.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
