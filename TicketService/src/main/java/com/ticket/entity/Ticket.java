package com.ticket.entity;

import com.ticket.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String ticketId;
    private int ticketNo;
    private String emailId;
    private String mobileNo;
    private String userId;
    private List<Long> travellers;
    private String busName;
    private Integer busId;
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
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "ticket_id")
    private List<Passenger> passengers;
}
