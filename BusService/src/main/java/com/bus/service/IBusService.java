package com.bus.service;

import com.bus.entity.Bus;
import com.bus.entity.BusStop;
import com.bus.request.BusRequest;
import com.bus.request.SourceAndDestinationRequest;
import com.bus.response.BusResponse;

import java.util.List;

public interface IBusService {
    BusResponse addBus(BusRequest busRequest);
    BusResponse getBusById(Integer busId);
    List<Bus> getAllBus();
    List<BusStop> getBusStopsById(Integer busId);
    void deleteByBusId(Integer busId);
    List<BusResponse> getBusIdBySourceAndDestination(SourceAndDestinationRequest destinationRequest);
    BusResponse updateBus(Integer busId, BusRequest busRequest);


}
