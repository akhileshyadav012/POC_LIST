package com.ticket.service.impl;

import com.ticket.dto.TicketDto;
import com.ticket.entity.Ticket;
import com.ticket.enums.TicketStatus;
import com.ticket.exception.NotFoundException;
import com.ticket.external.impl.UserServiceFeignClient;
import com.ticket.external.response.UserResponse;
import com.ticket.repository.TicketRepository;
import com.ticket.request.TicketRequest;
import com.ticket.response.TicketMessageResponse;
import com.ticket.response.TicketResponse;
import com.ticket.service.ITicketService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiceImpl implements ITicketService {
    private static final Logger logger = LogManager.getLogger(TicketServiceImpl.class);

    @Autowired
    private ITicketService ticketService;
    @Autowired
    private UserServiceFeignClient serviceFeignClient;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private RabbitMQProducerServiceImpl producerService;

    public String demoMethod(){
        logger.info("TicketServiceImpl - Inside demoMethod method");
        String number = String.valueOf(5 + 3);
        UserResponse userResponse = serviceFeignClient.getLoggedInUser();
        System.out.println("userResponse = " + userResponse);
        return number;
    }

    public TicketResponse createTicket(TicketRequest request){
        logger.info("TicketServiceImpl - Inside createTicket method");
        UserResponse userResponse = serviceFeignClient.getLoggedInUser();
        System.out.println("userResponse = " + userResponse);

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
        ticketRepository.save(ticket);

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

    public TicketResponse cancelTicket(String ticketId){
        logger.info("TicketServiceImpl - Inside cancelTicket method");
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);
        if (optionalTicket.isEmpty()){
            throw new NotFoundException("Ticket is not Present");
        }
        Ticket ticket = optionalTicket.get();
        double refundAmount = 0;
        ticket.setCancelTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.CANCELLED);
        ticketRepository.save(ticket);
        return TicketDto.convertToDto(ticket);
    }

    public TicketResponse getTicketByTicketId(String ticketId){
        logger.info("TicketServiceImpl - Inside getTicketByTicketId method");
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);
        if (optionalTicket.isEmpty()){
            throw new NotFoundException("Ticket is not Present");
        }
        Ticket ticket = optionalTicket.get();
        return TicketDto.convertToDto(ticket);
    }

    public TicketStatus getStatusByTicketId(String ticketId){
        logger.info("TicketServiceImpl - Inside getStatusByTicketId method");
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketId(ticketId);
        if (optionalTicket.isEmpty()){
            throw new NotFoundException("Ticket is not Present");
        }
        Ticket ticket = optionalTicket.get();
        return ticket.getStatus();
    }
}
