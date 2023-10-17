package com.rabbitmq.controller;

import com.rabbitmq.dto.UserDTO;
import com.rabbitmq.producer.RabbitMQJsonProducer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/json/message")
public class MessageJsonController {
    private static final Logger logger = LogManager.getLogger(MessageJsonController.class);

    @Autowired
    private RabbitMQJsonProducer rabbitMQJsonProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserDTO userDTO){
        logger.info("MessageJsonController - Inside sendJsonMessage method");
        rabbitMQJsonProducer.sendJsonMessage(userDTO);
        return ResponseEntity.ok("Json Message Send Successfully!!!");
    }
}
