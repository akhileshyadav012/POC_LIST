package com.ticket.service;

import com.ticket.request.TicketRequest;
import com.ticket.response.TicketResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ITicketService {
    String demoMethod();
    TicketResponse createTicket(TicketRequest request);
}
