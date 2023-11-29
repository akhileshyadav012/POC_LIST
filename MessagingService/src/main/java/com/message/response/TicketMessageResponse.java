package com.message.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.message.config.LocalDateSerializer;
import com.message.config.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketMessageResponse {
    private int ticketNo;
    private List<Long> travellers;
    private String busName;
    private String source;
    private String destination;
    @JsonDeserialize(using = LocalTimeSerializer.class)
    private LocalTime sourceTime;
    @JsonDeserialize(using = LocalTimeSerializer.class)
    private LocalTime destinationTime;
    @JsonDeserialize(using = LocalDateSerializer.class)
    private LocalDate journeyDate;
    private double distance;
    private double ticketFare;
    private String name;
}
