package com.consumer.service;

import com.consumer.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    private static final Logger logger = LogManager.getLogger(ConsumerService.class);

    @RabbitListener(queues = {"${one.queue.name}"})
    public void receiveMessage(String message){
//        logger.info("ConsumerService - Inside receiveMessage method");
        logger.info("Message Received = " + message);
    }

    @RabbitListener(queues = {"${two.queue.name}"})
    public void messageTwo(String message){
        logger.info("Message Received = " + message);
    }

    @RabbitListener(queues = {"${mobile.queue}"})
    public void productConsumedMessage(String product){
        logger.info("ConsumerService - Inside productConsumedMessage method");
        logger.info("Consumed Product = " + product);
    }

}
