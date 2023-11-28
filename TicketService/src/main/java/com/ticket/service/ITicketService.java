package com.ticket.service;

import com.ticket.entity.SeatDetails;
import com.ticket.enums.TicketStatus;
import com.ticket.request.TicketRequest;
import com.ticket.response.TicketResponse;

import java.time.LocalDate;

public interface ITicketService {
    String demoMethod();
    TicketResponse createTicket(TicketRequest request);
    TicketResponse cancelTicket(String ticketId);
    TicketResponse getTicketByTicketId(String ticketId);
    TicketStatus getStatusByTicketId(String ticketId);
    SeatDetails getSeatDetailsByDate(Integer busId, LocalDate bookingDate);
}
