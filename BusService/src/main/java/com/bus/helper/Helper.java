package com.bus.helper;

import com.bus.entity.Bus;
import com.bus.response.BusResponse;

public class Helper {

    public static BusResponse convertEntitytoDto(Bus bus){
        BusResponse busResponse = BusResponse.builder()
                .busId(bus.getBusId())
                .busNo(bus.getBusNo())
                .busName(bus.getBusName())
                .totalSeats(bus.getTotalSeats())
                .availableDays(bus.getAvailableDays())
                .hailStops(bus.getHaltStops())
                .busRoutes(bus.getBusRoutes())
                .status(bus.getStatus())
                .build();
        return busResponse;
    }
}
