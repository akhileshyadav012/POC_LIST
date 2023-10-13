package com.deliveryboy.controller;

import com.deliveryboy.service.IKafkaService;
import com.deliveryboy.service.impl.KafkaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/v1/location")
public class KafkaController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private IKafkaService kafkaservice;

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation(){
        logger.info("LocationController - Inside updateLocation method");
        this.kafkaservice.updateLocation("{ " + Math.round(Math.random()*100) +" , "+ Math.round(Math.random()*100) + " } ");
        return new ResponseEntity<>(Map.of("Message", " Location Updated"), HttpStatus.OK);
    }

    @PostMapping("/name")
    public ResponseEntity<?> updateName(@RequestBody String name){
        logger.info("LocationController - Inside updateName method");
        kafkaservice.updateName(name);
        return new ResponseEntity<>(Map.of("Message", " Location Updated"), HttpStatus.OK);
    }
}
