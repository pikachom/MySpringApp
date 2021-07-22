package com.spring.my.app.api.service;


import com.spring.my.app.api.model.Sample;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SampleService {
    public List<Sample> getSampleList();
    public Sample addSample(@RequestBody Sample sample);
    public List<Sample> getSampleByName(String name);

}
