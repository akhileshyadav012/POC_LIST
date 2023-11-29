package com.ticket.service.impl;

import com.ticket.dto.TicketDto;
import com.ticket.entity.OrderDetails;
import com.ticket.entity.SeatDetails;
import com.ticket.entity.Ticket;
import com.ticket.enums.TicketStatus;
import com.ticket.exception.NotFoundException;
import com.ticket.external.enums.BookStatus;
import com.ticket.external.impl.BusServiceFeignClient;
import com.ticket.external.impl.PaymentServiceFeignClient;
import com.ticket.external.impl.UserServiceFeignClient;
import com.ticket.external.response.BusResponse;
import com.ticket.external.response.UserResponse;
import com.ticket.repository.SeatDetailsRepository;
import com.ticket.repository.TicketRepository;
import com.ticket.request.CreateOrderRequest;
import com.ticket.request.TicketRequest;
import com.ticket.response.TicketMessageResponse;
import com.ticket.response.TicketResponse;
import com.ticket.service.ITicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements ITicketService {
    private static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);
    @Autowired
    private ITicketService ticketService;
    @Autowired
    private UserServiceFeignClient serviceFeignClient;
    @Autowired
    private BusServiceFeignClient busServiceFeignClient;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private RabbitMQProducerServiceImpl producerService;
    @Autowired
    private SeatDetailsRepository seatDetailsRepository;
    @Autowired
    private PaymentServiceFeignClient paymentServiceFeignClient;

    public String demoMethod() {
        logger.info("TicketServiceImpl - Inside demoMethod method");
        String number = String.valueOf(5 + 3);
//        BusResponse busResponse = busServiceFeignClient.getBusById(1);
//        System.out.println("busResponse = " + busResponse);
        OrderDetails orderDetails = paymentServiceFeignClient.createOrder(new CreateOrderRequest(BigDecimal.valueOf(12.32)));
        System.out.println("orderDetails = " + orderDetails);
        return number;
    }

    public TicketResponse createTicket(TicketRequest request) {
        logger.info("TicketServiceImpl - Inside createTicket method");
        UserResponse userResponse = serviceFeignClient.getLoggedInUser();
        System.out.println("userResponse = " + userResponse);
        BusResponse busResponse = busServiceFeignClient.getBusById(request.getBusId());
        System.out.println("busResponse = " + busResponse);

        Ticket ticket = Ticket.builder()
                .ticketId(String.valueOf(UUID.randomUUID()))
                .ticketNo(request.getTicketNo())
                .emailId(userResponse.getEmail())
                .mobileNo(userResponse.getMobileNo())
                .userId(userResponse.getUserId())
                .travellers(request.getTravellers())
                .busName(request.getBusName())
                .busId(request.getBusId())
                .source(request.getSource())
                .destination(request.getDestination())
                .sourceTime(request.getSourceTime())
                .destinationTime(request.getDestinationTime())
                .journeyDate(request.getJourneyDate())
                .distance(request.getDistance())
                .ticketFare(request.getTicketFare())
                .bookAmount(request.getBookAmount())
                .refundAmount(request.getRefundAmount())
                .bookTime(LocalDateTime.now())
                .status(TicketStatus.BOOKED)
                .passengers(request.getPassengers())
                .build();
        SeatDetails seatDetails = bookSeatDetails(request.getTravellers(), busResponse, request.getJourneyDate());
        System.out.println("seatDetails = " + seatDetails);

        OrderDetails orderDetails = paymentServiceFeignClient.createOrder(new CreateOrderRequest(BigDecimal.valueOf(ticket.getTicketFare())));
        String status = orderDetails.getStatus();
        if (status.equalsIgnoreCase("created")){
            ticketRepository.save(ticket);
        }
        TicketMessageResponse messageResponse = new TicketMessageResponse();
        messageResponse.setTicketNo(ticket.getTicketNo());
        messageResponse.setTravellers(ticket.getTravellers());
        messageResponse.setBusName(ticket.getBusName());
        messageResponse.setSource(ticket.getSource());
        messageResponse.setDestination(ticket.getDestination());
        messageResponse.setSourceTime(ticket.getSourceTime());
        messageResponse.setDestinationTime(ticket.getDestinationTime());
        messageResponse.setJourneyDate(ticket.getJourneyDate());
        messageResponse.setDistance(ticket.getDistance());
        messageResponse.setTicketFare(ticket.getTicketFare());
        String name = userResponse.getFirstName() + " " + userResponse.getLastName();
        System.out.println("name = "+  name);
        messageResponse.setName(name);
        producerService.sendTicketDetails(messageResponse);

        return TicketDto.convertToDto(ticket);
    }

    public TicketResponse cancelTicket(String ticketId) {
        logger.info("TicketServiceImpl - Inside cancelTicket method");
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new NotFoundException("Ticket is not Present");
        }
        Ticket ticket = optionalTicket.get();
        double refundAmount = 0;
        ticket.setCancelTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.CANCELLED);
        ticketRepository.save(ticket);
        return TicketDto.convertToDto(ticket);
    }

    public TicketResponse getTicketByTicketId(String ticketId) {
        logger.info("TicketServiceImpl - Inside getTicketByTicketId method");
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new NotFoundException("Ticket is not Present");
        }
        Ticket ticket = optionalTicket.get();
        return TicketDto.convertToDto(ticket);
    }

    public TicketStatus getStatusByTicketId(String ticketId) {
        logger.info("TicketServiceImpl - Inside getStatusByTicketId method");
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new NotFoundException("Ticket is not Present");
        }
        Ticket ticket = optionalTicket.get();
        return ticket.getStatus();
    }

    public SeatDetails getSeatDetailsByDate(Integer busId, LocalDate bookingDate){
        logger.info("TicketServiceImpl - Inside getSeatDetailsByDate method");
        Optional<SeatDetails> optionalSeatDetails = seatDetailsRepository.findByBusIdAndBookingDate(busId, bookingDate);
        if (optionalSeatDetails.isEmpty()){
            throw new NotFoundException("Seat Details are not available on this Date");
        }
        return optionalSeatDetails.get();
    }

    private static String convertListToString(List<String> ids) {
        return ids.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    public SeatDetails bookSeatDetails(List<Long> travellers, BusResponse busResponse, LocalDate bookingDate) {
        logger.info("BusServiceImpl - Inside updateBus method");
        Optional<SeatDetails> optionalSeatDetails = seatDetailsRepository.findByBusIdAndBookingDate(busResponse.getBusId(), bookingDate);

        if (optionalSeatDetails.isEmpty()){
            Map<Long, BookStatus> bookStatusMap = new HashMap<>();
            for (Long seatNo : travellers) {
                if (seatNo > busResponse.getTotalSeats()){
                    throw new NotFoundException("Enter Valid SeatNo");
                }
                bookStatusMap.put(seatNo, BookStatus.BOOKED);
            }
            for (Map.Entry<Long, BookStatus> m : bookStatusMap.entrySet()) {
                System.out.println(m.getKey() + " - " + m.getValue());
            }
            SeatDetails seatDetails = new SeatDetails();
            seatDetails.setBusId(busResponse.getBusId());
            seatDetails.setBookingDate(LocalDate.now());
            seatDetails.setSeatDetails(bookStatusMap.toString());

            seatDetailsRepository.save(seatDetails);
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

            for (Long seatNo : travellers){
                bookStatusMap.put(seatNo, BookStatus.BOOKED);
            }

            seatDetails.setSeatDetails(bookStatusMap.toString());
            seatDetailsRepository.save(seatDetails);

        return seatDetails;
    }
}
