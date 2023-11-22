package com.ticket.service;

import com.ticket.response.TicketMessageResponse;

public interface IRabbitMQProducerService {

    void sendTicketDetails(TicketMessageResponse messageResponse);
}
