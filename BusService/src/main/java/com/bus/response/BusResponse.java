package com.bus.response;

import com.bus.entity.BusRoute;
import com.bus.entity.BusStop;
import com.bus.enums.BusStatus;
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
public class BusResponse {
    private String busId;
    private Integer busNo;
    private String busName;
    private String routeFrom;
    private String routeTo;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer totalSeats;
    private Integer availableSeats;
    private List<String> availableDays;
    private List<BusStop> hailStops;
    private List<BusRoute> busRoutes;
    private BusStatus status;

}
