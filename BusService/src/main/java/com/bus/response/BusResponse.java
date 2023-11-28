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
    private Integer busId;
    private String busNo;
    private String busName;
    private Integer totalSeats;
    private String availableDays;
    private List<BusStop> hailStops;
    private List<BusRoute> busRoutes;
    private BusStatus status;

}
