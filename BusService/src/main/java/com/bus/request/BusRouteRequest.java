package com.bus.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusRouteRequest {

//    private UUID routeId;
    private String routeFrom;
    private String routeTo;
    private double distance;
    private double fare;
}
