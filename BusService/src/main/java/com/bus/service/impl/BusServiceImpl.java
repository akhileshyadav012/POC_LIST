package com.bus.service.impl;

import com.bus.constants.BusConstants;
import com.bus.entity.Bus;
import com.bus.entity.BusStop;
import com.bus.entity.SeatDetails;
import com.bus.enums.BookStatus;
import com.bus.enums.BusStatus;
import com.bus.exception.NotFoundException;
import com.bus.helper.Helper;
import com.bus.repository.BusRepository;
import com.bus.repository.BusRouteRepository;
import com.bus.repository.BusStopRepository;
import com.bus.repository.SeatDetailsRepository;
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
    @Autowired
    private BusStopRepository busStopRepository;

    @Autowired
    private SeatDetailsRepository seatDetailsRepository;
    public BusResponse addBus(BusRequest busRequest){
        logger.info("BusServiceImpl - Inside addBus method");
        Bus bus = new Bus();
        bus.setBusNo(busRequest.getBusNo());
        bus.setBusName(busRequest.getBusName());
        bus.setTotalSeats(busRequest.getTotalSeats());
        bus.setAvailableDays(convertListToString(busRequest.getAvailableDays()));
        bus.setStatus(BusStatus.ACTIVE);
        bus.setHaltStops(busRequest.getHailStops());
        bus.setBusRoutes(busRequest.getBusRoutes());
        busRepository.save(bus);

        BusResponse busResponse = Helper.convertEntitytoDto(bus);
        return busResponse;
    }
    
    public BusResponse getBusById(Integer busId){
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

    public List<BusStop> getBusStopsById(Integer busId){
        logger.info("BusServiceImpl - Inside getBusById method");
        Optional<Bus> optionalBus = busRepository.findByBusId(busId);
        if (optionalBus.isEmpty()){
            throw new NotFoundException(BusConstants.ERR_NOT_FOUND);
        }
        Bus bus = optionalBus.get();
        List<BusStop> haltStops = bus.getHaltStops();
        return haltStops;
    }

    public void deleteByBusId(Integer busId){
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
            Bus bus = busRepository.findByBusIdAndStatus(getBus.getBus_Id(), BusStatus.ACTIVE);
            if (Objects.nonNull(bus) && bus.getAvailableDays().contains(dayOfWeek)){
                BusResponse busResponse = Helper.convertEntitytoDto(bus);
                busResponseList.add(busResponse);
            }
        }
        return busResponseList;
    }

    private static String convertListToString(List<String> ids) {
        return ids.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    public BusResponse updateBus(Integer busId, BusRequest busRequest){
        logger.info("BusServiceImpl - Inside updateBus method");
        if (busRequest.getBusRoutes() != null){
            busRouteRepository.deleteBusRoute(busId);
        }
        if (busRequest.getHailStops() !=  null){
            busStopRepository.deleteBusStops(busId);
        }
        Optional<Bus> optionalBus = busRepository.findByBusId(busId);
        if (optionalBus.isEmpty()){
            throw new NotFoundException("Bus not found to update");
        }
        Bus bus = optionalBus.get();
        bus.setBusNo(bus.getBusNo());
        bus.setBusName(bus.getBusName());
        bus.setTotalSeats(busRequest.getTotalSeats());
        bus.setAvailableDays(convertListToString(busRequest.getAvailableDays()));
        bus.setHaltStops(busRequest.getHailStops());
        bus.setBusRoutes(busRequest.getBusRoutes());
        busRepository.save(bus);
        return Helper.convertEntitytoDto(bus);
    }

    public SeatDetails bookSeatDetails(List<Long> seatCapacity){
        logger.info("BusServiceImpl - Inside updateBus method");

        List<SeatDetails> seatDetailsList = new ArrayList<>();

        Map<Long, BookStatus> bookStatusMap = new HashMap<>();
        for (Long seatNo : seatCapacity){
            bookStatusMap.put(seatNo, BookStatus.BOOKED);
        }
        for (Map.Entry<Long, BookStatus> m: bookStatusMap.entrySet()){
            System.out.println(m.getKey() + " - " + m.getValue());
        }

        SeatDetails seatDetails = new SeatDetails();
        seatDetails.setBusId(1);
        seatDetails.setBookingDate(LocalDate.now());
        seatDetails.setSeatDetails(bookStatusMap.toString());

        seatDetailsRepository.save(seatDetails);
        return seatDetails;
    }

    public SeatDetails updateBookSeatDetails(LocalDate date, List<Long> seatCapacity){
        logger.info("BusServiceImpl - Inside updateBookSeatDetails method");
        Optional<SeatDetails> optionalSeatDetails = seatDetailsRepository.findByBookingDate(date);
        if (optionalSeatDetails.isEmpty()){
            throw new NotFoundException("details not present");
        }
        SeatDetails seatDetails = optionalSeatDetails.get();
        String bookingSeatDetails = seatDetails.getSeatDetails();
        System.out.println("bookingSeatDetails = " + bookingSeatDetails);

        Map<Long, BookStatus> bookStatusMap = new HashMap<>();
        String[] keyValuePairs = bookingSeatDetails.replaceAll("[{}]", "").split(", ");

        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            Long key = Long.parseLong(entry[0]);
            BookStatus value = BookStatus.valueOf(entry[1]);
            bookStatusMap.put(key, value);
        }
        System.out.println(bookStatusMap);

        for (Long seatNo : seatCapacity){
            bookStatusMap.put(seatNo, BookStatus.BOOKED);
        }

        seatDetails.setSeatDetails(bookStatusMap.toString());
        seatDetailsRepository.save(seatDetails);
        return seatDetails;
    }
}
