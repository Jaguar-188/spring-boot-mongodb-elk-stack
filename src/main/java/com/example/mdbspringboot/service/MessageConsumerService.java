package com.example.mdbspringboot.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {

//    @KafkaListener(topics = "test", groupId = "demo")
//    public void consumeMessage(String message){
//        System.out.println("Message : " + message);
//    }
}
