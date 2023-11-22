package com.ticket.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ticket.config.LocalDateSerializer;
import com.ticket.config.LocalTimeSerializer;
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
public class TicketMessageResponse {
    private int ticketNo;
    private int travellers;
    private String busName;
    private String source;
    private String destination;

    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime sourceTime;

    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime destinationTime;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate journeyDate;
    private double distance;
    private double ticketFare;
    private String name;
}
