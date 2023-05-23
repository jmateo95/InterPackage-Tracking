package com.interpackage.tracking.producers;

import com.interpackage.basedomains.dto.RouteEvent;
import com.interpackage.basedomains.dto.TrackingEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class TrackingProducer {

    private final NewTopic topic;
    private final KafkaTemplate<String, TrackingEvent> kafkaTemplate;

    public TrackingProducer(NewTopic topic, KafkaTemplate<String, TrackingEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TrackingEvent event){
        //Message
        Message<TrackingEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
