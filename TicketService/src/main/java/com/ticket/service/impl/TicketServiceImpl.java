package com.ticket.service.impl;

import com.ticket.dto.TicketDto;
import com.ticket.entity.Ticket;
import com.ticket.enums.TicketStatus;
import com.ticket.external.impl.UserServiceFeignClient;
import com.ticket.external.response.UserResponse;
import com.ticket.repository.TicketRepository;
import com.ticket.request.TicketRequest;
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

        return TicketDto.convertToDto(ticket);
    }
}
