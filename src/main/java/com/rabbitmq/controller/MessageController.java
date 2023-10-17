package com.rabbitmq.controller;

import com.rabbitmq.producer.RabbitMQProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/message")
public class MessageController {
    private static final Logger logger = LogManager.getLogger(MessageController.class);
    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    public MessageController(RabbitMQProducer rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam(name = "message") String message){
        logger.info("MessageController - Inside sendMessage method");
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message send Successfully");
    }

}
