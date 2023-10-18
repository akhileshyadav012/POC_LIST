package com.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.producer.entity.Product;
import com.producer.service.ProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/produce")
public class ProducerController {
    private static final Logger logger = LogManager.getLogger(ProducerController.class);
    @Autowired
    private ProducerService producerService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestParam(name = "message") String message){
        logger.info("ProducerController - Inside sendMessage method");
        for (int i=1; i<=100; i++){
            producerService.sendMessage(message);
        }
        logger.info("message = " + message.toString());
        return ResponseEntity.ok("Message send successfully by producer");
    }

    @PostMapping("/two")
    public ResponseEntity<String> messageTwo(@RequestParam(name = "message")String message, @RequestParam(name = "num")int num){
        logger.info("ProducerController - Inside messageTwo method");
        producerService.messageTwo(message, num);
        logger.info("message send = " + message + " " + num);
        return ResponseEntity.ok("Message send successfully by producer");
    }

    @PostMapping("/product")
    public ResponseEntity<String> productMessage(@RequestBody Product product) throws JsonProcessingException {
        logger.info("ProducerController - Inside productMessage method");
        producerService.productMessage(product);
        return ResponseEntity.ok("Product send successfully");

    }

}