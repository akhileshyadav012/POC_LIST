package com.bus.controller;

import com.bus.request.BusRequest;
import com.bus.response.BusResponse;
import com.bus.service.IBusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bus")
public class BusController {
    private static final Logger logger = LogManager.getLogger(BusController.class);

    @Autowired
    private IBusService busService;

    @PostMapping
    public ResponseEntity<BusResponse> addBus(@RequestBody BusRequest busRequest) {
        BusResponse busResponse = busService.addBus(busRequest);
        return ResponseEntity.ok(busResponse);
    }

}
