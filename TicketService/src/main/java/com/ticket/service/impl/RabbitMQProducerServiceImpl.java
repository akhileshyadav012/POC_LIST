package com.ticket.service.impl;

import com.ticket.response.TicketMessageResponse;
import com.ticket.service.IRabbitMQProducerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerServiceImpl implements IRabbitMQProducerService {
    private static final Logger logger = LogManager.getLogger(RabbitMQProducerServiceImpl.class);

    @Value("${ticket.queue.name}")
    private String queueName;
    @Value("${ticket.exchange.name}")
    private String queueExchange;
    @Value("${ticket.routing.key}")
    private String queueRouting;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducerServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTicketDetails(TicketMessageResponse messageResponse){
        logger.info("RabbitMQProducerServiceImpl - Inside sendTicketDetails method");
        logger.info("message details send = " + messageResponse.toString());
        rabbitTemplate.convertAndSend(queueExchange, queueRouting, messageResponse);
    }
}
