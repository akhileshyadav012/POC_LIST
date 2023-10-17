package com.rabbitmq.config;

import com.rabbitmq.dto.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {
    private static final Logger logger = LogManager.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(UserDTO userDTO){
        logger.info("RabbitMQJsonConsumer - Inside consumeJsonMessage method");
        logger.info("Consumed Message = " + userDTO);
    }

}
