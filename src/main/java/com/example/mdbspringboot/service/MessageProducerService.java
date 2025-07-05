package com.example.mdbspringboot.service;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopic = "test";

    public MessageProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String messageToProduce){

        kafkaTemplate.send(kafkaTopic,messageToProduce);
    }
}
