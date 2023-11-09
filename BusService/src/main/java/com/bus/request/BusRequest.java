package com.bus.request;

import com.bus.entity.BusRoute;
import com.bus.entity.BusStop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusRequest {
    private String busName;
    private String source;
    private String destination;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer totalSeats;
    private List<String> availableDays;
    private List<BusStop> hailStops;
    private List<BusRoute> busRoutes;



}
