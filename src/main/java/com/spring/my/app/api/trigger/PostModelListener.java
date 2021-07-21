package com.spring.my.app.api.trigger;

import com.spring.my.app.api.model.Posting;
import com.spring.my.app.api.service.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class PostModelListener extends AbstractMongoEventListener<Posting> {

    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Posting> event){
        event.getSource().set_id(sequenceGeneratorService.generateSequence(Posting.SEQUENCE_NAME));
    }
}
