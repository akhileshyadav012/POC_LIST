package com.bus.service;

import com.bus.request.BusRequest;
import com.bus.response.BusResponse;

public interface IBusService {
    BusResponse addBus(BusRequest busRequest);

}
