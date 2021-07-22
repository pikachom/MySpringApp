package com.spring.my.app.api.service;

import com.spring.my.app.api.model.Posting;
import com.spring.my.app.api.repository.PostingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Slf4j
@Service
public class PostingService {

    @Autowired
    private PostingRepository postingRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    public List<Posting> getAllPosting(){
        return postingRepository.findAll();
    }
    public Posting getPosting(String id){
        return postingRepository.findById(Long.valueOf(id)).orElseThrow();
    }
    public Posting insertPosting(Posting posting){
        log.info(posting.toString());
        Long seqval = sequenceGeneratorService.generateSequence(Posting.SEQUENCE_NAME);
        log.info("sequence is {}", seqval);
        posting.setId(seqval);
        log.info(posting.toString());
        return postingRepository.save(posting);
    }
}
