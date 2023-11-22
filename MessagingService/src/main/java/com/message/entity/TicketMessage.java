package com.message.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.message.config.LocalDateSerializer;
import com.message.config.LocalTimeSerializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TicketMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private int ticketNo;
    private int travellers;
    private String busName;
    private String source;
    private String destination;
    private LocalTime sourceTime;
    private LocalTime destinationTime;
    private LocalDate journeyDate;
    private double distance;
    private double ticketFare;
    private String name;
}
