package com.producer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.producer.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private static final Logger logger = LogManager.getLogger(ProducerService.class);
    @Value("${one.exchange.name}")
    private String oneExchange;
    @Value("${one.routing.key}")
    private String oneRoutingKey;
    @Value("${two.routing.key}")
    private String twoRoutingKey;

    @Value("${mobile.exchange}")
    private String mobileExchange;
    @Value("${mobile.routing.key}")
    private String mobileRoutingKey;

    private RabbitTemplate rabbitTemplate;
    public ProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        logger.info("ProducerService - Inside sendMessage method");
        rabbitTemplate.convertAndSend(oneExchange, oneRoutingKey, message);
    }

    public void messageTwo(String message, int num){
        logger.info("ProducerService - Inside messageTwo method");
        String finalMessage = message + " " + num;
        rabbitTemplate.convertAndSend(oneExchange, twoRoutingKey, finalMessage);
    }

    public void productMessage(Product product) throws JsonProcessingException {
        logger.info("ProducerService - Inside productMessage method");
        ObjectMapper objectMapper = new ObjectMapper();
        String productString = objectMapper.writeValueAsString(product);
        logger.info("product as string {}", productString);
        rabbitTemplate.convertAndSend(mobileExchange, mobileRoutingKey, productString);
//        rabbitTemplate.convertAndSend(mobileExchange, mobileRoutingKey, product.toString); OR this is also ok in 1 line

    }

}
