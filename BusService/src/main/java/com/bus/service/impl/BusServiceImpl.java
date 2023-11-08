package com.bus.service.impl;

import com.bus.entity.Bus;
import com.bus.enums.BusStatus;
import com.bus.repository.BusRepository;
import com.bus.request.BusRequest;
import com.bus.response.BusResponse;
import com.bus.service.IBusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class BusServiceImpl implements IBusService {
    private static final Logger logger = LogManager.getLogger(BusServiceImpl.class);

    @Autowired
    private BusRepository busRepository;
    public BusResponse addBus(BusRequest busRequest){
        logger.info("BusServiceImpl - Inside addBus method");
        System.out.println("employee : " + busRequest);
        Random random = new Random();
        Integer busNo  = random. nextInt(900) + 100;
        String busId = String.valueOf(UUID.randomUUID());
        Bus bus = new Bus();
        bus.setBusId(busId);
        bus.setBusNo(busNo);
        bus.setBusName(busRequest.getBusName());
        bus.setRouteFrom(busRequest.getRouteFrom());
        bus.setRouteTo(busRequest.getRouteTo());
        bus.setArrivalTime(busRequest.getArrivalTime());
        bus.setDepartureTime(busRequest.getDepartureTime());
        bus.setTotalSeats(busRequest.getTotalSeats());
        bus.setAvailableSeats(busRequest.getTotalSeats());
        bus.setAvailableDays(busRequest.getAvailableDays());
        bus.setStatus(BusStatus.ACTIVE);
        bus.setHaltStops(busRequest.getHailStops());
        bus.setBusRoutes(busRequest.getBusRoutes());
        busRepository.save(bus);

        BusResponse busResponse = BusResponse.builder()
                .busId(bus.getBusId())
                .busNo(bus.getBusNo())
                .busName(bus.getBusName())
                .routeFrom(bus.getRouteFrom())
                .routeTo(bus.getRouteTo())
                .arrivalTime(bus.getArrivalTime())
                .departureTime(bus.getDepartureTime())
                .totalSeats(bus.getTotalSeats())
                .availableSeats(bus.getAvailableSeats())
                .availableDays(bus.getAvailableDays())
                .hailStops(bus.getHaltStops())
                .busRoutes(bus.getBusRoutes())
                .status(bus.getStatus())
                .build();
        return busResponse;
    }

}
