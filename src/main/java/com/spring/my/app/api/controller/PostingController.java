package com.spring.my.app.api.controller;


import com.spring.my.app.api.model.Posting;
import com.spring.my.app.api.service.PostingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posting")
public class PostingController {

    @Autowired
    private PostingService postingService;

    @GetMapping
    List<Posting> getAllSampleList(){
        return postingService.getAllPosting();
    }
    @PostMapping
    Posting postPosting(@RequestBody Posting posting){
        return postingService.insertPosting(posting);
    }
    @GetMapping("/{_id}")
    Posting getOnePosting(@PathVariable String _id){
        return postingService.getPosting(_id);
    }
}
