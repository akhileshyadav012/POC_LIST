package com.bus.service;

import com.bus.entity.Bus;
import com.bus.entity.BusStop;
import com.bus.entity.SeatDetails;
import com.bus.enums.BookStatus;
import com.bus.request.BusRequest;
import com.bus.request.SourceAndDestinationRequest;
import com.bus.response.BusResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IBusService {
    BusResponse addBus(BusRequest busRequest);
    BusResponse getBusById(Integer busId);
    List<Bus> getAllBus();
    List<BusStop> getBusStopsById(Integer busId);
    void deleteByBusId(Integer busId);
    List<BusResponse> getBusIdBySourceAndDestination(SourceAndDestinationRequest destinationRequest);
    BusResponse updateBus(Integer busId, BusRequest busRequest);
    SeatDetails bookSeatDetails(List<Long> seatCapacity);
    SeatDetails updateBookSeatDetails(LocalDate date, List<Long> seatCapacity);


}
