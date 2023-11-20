package com.ticket.response;

import com.ticket.entity.Passenger;
import com.ticket.enums.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {
    private int ticketNo;
    private String emailId;
    private String mobileNo;
    private String userId;
    private int travellers;
    private String busName;
    private String busId;
    private String source;
    private String destination;
    private LocalTime sourceTime;
    private LocalTime destinationTime;
    private LocalDate journeyDate;
    private double distance;
    private double ticketFare;
    private double bookAmount;
    private double refundAmount;
    private LocalDateTime bookTime;
    private LocalDateTime cancelTime;
    private TicketStatus status;
    private List<Passenger> passengers;
}
