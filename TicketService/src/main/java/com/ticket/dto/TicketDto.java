package com.ticket.dto;

import com.ticket.entity.Ticket;
import com.ticket.response.TicketResponse;
import lombok.*;

@Data
@Builder
public class TicketDto {

    public static TicketResponse convertToDto(Ticket ticket){

        return TicketResponse.builder()
                .ticketNo(ticket.getTicketNo())
                .emailId(ticket.getEmailId())
                .mobileNo(ticket.getMobileNo())
                .userId(ticket.getUserId())
                .travellers(ticket.getTravellers())
                .busName(ticket.getBusName())
                .busId(ticket.getBusId())
                .source(ticket.getSource())
                .destination(ticket.getDestination())
                .sourceTime(ticket.getSourceTime())
                .destinationTime(ticket.getDestinationTime())
                .journeyDate(ticket.getJourneyDate())
                .distance(ticket.getDistance())
                .ticketFare(ticket.getTicketFare())
                .bookAmount(ticket.getBookAmount())
                .refundAmount(ticket.getRefundAmount())
                .bookTime(ticket.getBookTime())
                .cancelTime(ticket.getCancelTime())
                .status(ticket.getStatus())
                .passengers(ticket.getPassengers())
                .build();
    }
}
