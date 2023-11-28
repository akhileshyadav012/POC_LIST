package com.bus.controller;

import com.bus.entity.Bus;
import com.bus.entity.BusStop;
import com.bus.entity.SeatDetails;
import com.bus.enums.BookStatus;
import com.bus.request.BusRequest;
import com.bus.request.SourceAndDestinationRequest;
import com.bus.response.BusResponse;
import com.bus.service.IBusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/bus")
public class BusController {
    private static final Logger logger = LogManager.getLogger(BusController.class);

    @Autowired
    private IBusService busService;

    @PostMapping
    public ResponseEntity<BusResponse> addBus(@RequestBody BusRequest busRequest) {
        logger.info("BusController - Inside addBus method");
        BusResponse busResponse = busService.addBus(busRequest);
        return ResponseEntity.ok(busResponse);
    }

    @GetMapping("/{busId}")
    public ResponseEntity<BusResponse> getBusById(@PathVariable(name = "busId") Integer busId){
        logger.info("BusController - Inside getBusById method");
        BusResponse response = busService.getBusById(busId);
        return ResponseEntity.of(Optional.ofNullable(response));
    }

    @GetMapping
    public ResponseEntity<List<Bus>> getAllBus(){
        logger.info("BusController - Inside getAllBus method");
        List<Bus> busList = busService.getAllBus();
        return ResponseEntity.of(Optional.ofNullable(busList));
    }
    @GetMapping("/stops/{busId}")
    public ResponseEntity<List<BusStop>> getBusStopsById(@PathVariable(name = "busId") Integer busId){
        logger.info("BusController - Inside getBusById method");
        List<BusStop> busStopList = busService.getBusStopsById(busId);
        return ResponseEntity.of(Optional.ofNullable(busStopList));
    }

    @DeleteMapping("/{busId}")
    public ResponseEntity<Void> deleteBusById(@PathVariable(name = "busId") Integer busId){
        logger.info("BusController - Inside deleteBusById method");
        busService.deleteByBusId(busId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/busId")
    public ResponseEntity<List<BusResponse>> getBusIdBySourceAndDestination(@RequestBody SourceAndDestinationRequest destinationRequest){
        logger.info("BusController - Inside getBusIdBySourceAndDestination method");
        List<BusResponse> busResponseList = busService.getBusIdBySourceAndDestination(destinationRequest);
        return ResponseEntity.of(Optional.ofNullable(busResponseList));
    }

    @PutMapping("/update/{busId}")
    public ResponseEntity<BusResponse> updateBus(@PathVariable(name = "busId") Integer busId ,@RequestBody BusRequest busRequest) {
        logger.info("BusController - Inside addBus method");
        BusResponse busResponse = busService.updateBus(busId, busRequest);
        return ResponseEntity.ok(busResponse);
    }

    @GetMapping("/capacity/{seatCapacity}")
    public ResponseEntity<SeatDetails> seatCapacity(@PathVariable(name = "seatCapacity") List<Long> seatCapacity){
        logger.info("BusController - Inside seatCapacity method");
        SeatDetails seatDetails = busService.bookSeatDetails(seatCapacity);
        return ResponseEntity.of(Optional.ofNullable(seatDetails));
    }

    @GetMapping("/updateCapacity")
    public ResponseEntity<SeatDetails> updateSeatDetails(@RequestParam(name = "date")LocalDate date, @RequestParam(name = "seatCapacity") List<Long> seatCapacity){
        logger.info("BusController - Inside updateSeatDetails method");
        SeatDetails seatDetails = busService.updateBookSeatDetails(date, seatCapacity);
        return ResponseEntity.of(Optional.ofNullable(seatDetails));
    }

}
