package com.bus.service.impl;

import com.bus.constants.BusConstants;
import com.bus.entity.Bus;
import com.bus.entity.BusStop;
import com.bus.enums.BusStatus;
import com.bus.exception.NotFoundException;
import com.bus.helper.Helper;
import com.bus.repository.BusRepository;
import com.bus.repository.BusRouteRepository;
import com.bus.request.BusRequest;
import com.bus.request.SourceAndDestinationRequest;
import com.bus.response.BusResponse;
import com.bus.response.GetBusesQueryResponse;
import com.bus.service.IBusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements IBusService {
    private static final Logger logger = LogManager.getLogger(BusServiceImpl.class);

    @Autowired
    private BusRouteRepository busRouteRepository;
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
        bus.setSource(busRequest.getSource());
        bus.setDestination(busRequest.getDestination());
        bus.setArrivalTime(busRequest.getArrivalTime());
        bus.setDepartureTime(busRequest.getDepartureTime());
        bus.setTotalSeats(busRequest.getTotalSeats());
        bus.setAvailableSeats(busRequest.getTotalSeats());
        bus.setAvailableDays(busRequest.getAvailableDays());
        bus.setStatus(BusStatus.ACTIVE);
        bus.setHaltStops(busRequest.getHailStops());
        bus.setBusRoutes(busRequest.getBusRoutes());
        busRepository.save(bus);

        BusResponse busResponse = Helper.convertEntitytoDto(bus);
        return busResponse;
    }
    
    public BusResponse getBusById(String busId){
        logger.info("BusServiceImpl - Inside getBusById method");
        Optional<Bus> optionalBus = busRepository.findByBusId(busId);
        if (optionalBus.isEmpty()){
            throw new NotFoundException(BusConstants.ERR_NOT_FOUND);
        }
        Bus bus = optionalBus.get();
        BusResponse busResponse = Helper.convertEntitytoDto(bus);
        return busResponse;
    }

    public List<Bus> getAllBus(){
        logger.info("BusServiceImpl - Inside getAllBus method");
        List<Bus> busList = busRepository.findAll();
        return busList;
    }

    public List<BusStop> getBusStopsById(String busId){
        logger.info("BusServiceImpl - Inside getBusById method");
        Optional<Bus> optionalBus = busRepository.findByBusId(busId);
        if (optionalBus.isEmpty()){
            throw new NotFoundException(BusConstants.ERR_NOT_FOUND);
        }
        Bus bus = optionalBus.get();
        List<BusStop> haltStops = bus.getHaltStops();
        return haltStops;
    }

    public void deleteByBusId(String busId){
        logger.info("BusServiceImpl - Inside deleteByBusId method");
        busRepository.deleteByBusId(busId);
    }

    public List<BusResponse> getBusIdBySourceAndDestination(SourceAndDestinationRequest destinationRequest){
        logger.info("BusServiceImpl - Inside getBusIdBySourceAndDestination method");
        List<GetBusesQueryResponse> busesIdList = busRouteRepository.getBusesIdsFromSourceAndDestination(destinationRequest.getSource(), destinationRequest.getDestination());
        LocalDate date = LocalDate.parse(destinationRequest.getDate(), DateTimeFormatter.ISO_DATE);
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        List<BusResponse> busResponseList = new ArrayList<>();
        for (GetBusesQueryResponse getBus : busesIdList){
            Bus bus = busRepository.findByIdAndStatus(getBus.getBus_Id(), BusStatus.ACTIVE);
            if (Objects.nonNull(bus) && bus.getAvailableDays().contains(dayOfWeek)){
                BusResponse busResponse = Helper.convertEntitytoDto(bus);
                busResponseList.add(busResponse);
            }
        }
        return busResponseList;
    }

}
