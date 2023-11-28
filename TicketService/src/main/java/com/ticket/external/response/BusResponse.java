package com.ticket.external.response;

import com.ticket.external.entity.BusRoute;
import com.ticket.external.entity.BusStop;
import com.ticket.external.enums.BusStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

