package com.ticket.controller;

import com.ticket.response.TicketMessageResponse;
import com.ticket.service.IRabbitMQProducerService;
import com.ticket.service.impl.RabbitMQProducerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/rabbit")
public class RabbitMQProducerController {
    private static final Logger logger = LogManager.getLogger(RabbitMQProducerServiceImpl.class);
    @Autowired
    private IRabbitMQProducerService producerService;

    @PostMapping("/send")
    public ResponseEntity<String> sendTicketDetails(@RequestBody TicketMessageResponse messageResponse){
        logger.info("RabbitMQProducerController - Inside sendTicketDetails method");
        producerService.sendTicketDetails(messageResponse);
        return ResponseEntity.of(Optional.ofNullable("Details Send Successfully"));
    }
}
