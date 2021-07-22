package com.spring.my.app.api.service;

import com.spring.my.app.api.model.DbSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName){
        //get seq number
        Query query = new Query(Criteria.where("id").is(seqName));
        //update one
        Update update = new Update().inc("seq", 1);
        //
        DbSequence counter = mongoOperations.findAndModify(
                        query,
                        update, options().returnNew(true).upsert(true),
                        DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
