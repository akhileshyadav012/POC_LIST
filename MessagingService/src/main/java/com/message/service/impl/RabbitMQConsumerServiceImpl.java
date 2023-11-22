package com.message.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.message.entity.TicketMessage;
import com.message.repository.TicketMessageRepository;
import com.message.response.TicketMessageResponse;
import com.message.service.IRabbitMQConsumerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumerServiceImpl implements IRabbitMQConsumerService {
    private static final Logger logger = LogManager.getLogger(RabbitMQConsumerServiceImpl.class);
    @Autowired
    private TicketMessageRepository ticketMessageRepository;

    @Value("${ticket.queue.name}")
    private String queueName;

    @RabbitListener(queues = {"${ticket.queue.name}"})
    public void consumeTicketDetails(TicketMessageResponse messageResponse) throws JsonProcessingException {
        logger.info("RabbitMQConsumerServiceImpl - Inside consumeTicketDetails method");
        logger.info("consumedTicketDetails = " + messageResponse);

        TicketMessage ticketMessage = TicketMessage.builder().ticketNo(messageResponse.getTicketNo())
                .travellers(messageResponse.getTravellers())
                .busName(messageResponse.getBusName())
                .source(messageResponse.getSource())
                .destination(messageResponse.getDestination())
                .sourceTime(messageResponse.getSourceTime())
                .destinationTime(messageResponse.getDestinationTime())
                .journeyDate(messageResponse.getJourneyDate())
                .distance(messageResponse.getDistance())
                .ticketFare(messageResponse.getTicketFare())
                .name(messageResponse.getName())
                .build();

        ticketMessageRepository.save(ticketMessage);
    }

}
