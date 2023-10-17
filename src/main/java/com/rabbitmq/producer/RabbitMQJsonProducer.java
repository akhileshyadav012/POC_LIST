package com.rabbitmq.producer;

import com.rabbitmq.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {
    private static final Logger logger = LogManager.getLogger(RabbitMQJsonProducer.class);
    @Value("${rabbitmq.exchange.json.name}")
    private String jsonExchange;
    @Value("${rabbitmq.routing.json.key}")
    private String jsonRoutingKey;
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(UserDTO userDTO){
        logger.info("RabbitMQJsonProducer - Inside sendJsonMessage method");
        logger.info("User = " + userDTO.toString());
        rabbitTemplate.convertAndSend(jsonExchange, jsonRoutingKey, userDTO);
    }
}
