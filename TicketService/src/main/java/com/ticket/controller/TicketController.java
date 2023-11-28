package com.ticket.controller;

import com.ticket.entity.SeatDetails;
import com.ticket.enums.TicketStatus;
import com.ticket.request.TicketRequest;
import com.ticket.response.TicketResponse;
import com.ticket.service.ITicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tickets")
public class TicketController {
    private static final Logger logger = LogManager.getLogger(TicketController.class);

    @Autowired
    private ITicketService ticketService;

    @GetMapping()
    public ResponseEntity<String> demoMethod() {
        logger.info("TicketController -Inside demoMethod method");
        String number = ticketService.demoMethod();
        return ResponseEntity.of(Optional.ofNullable(number));
    }

    @PostMapping()
    public ResponseEntity<TicketResponse> createTicket(@RequestBody TicketRequest request) {
        logger.info("TicketController - Inside createTicket method");
        TicketResponse ticketResponse = ticketService.createTicket(request);
        return ResponseEntity.of(Optional.ofNullable(ticketResponse));
    }

    @GetMapping("/cancel/{ticketId}")
    public ResponseEntity<TicketResponse> cancelTicket(@PathVariable("ticketId") String ticketId) {
        logger.info("TicketController - Inside cancelTicket method");
        TicketResponse ticketResponse = ticketService.cancelTicket(ticketId);
        return ResponseEntity.of(Optional.ofNullable(ticketResponse));
    }

    @GetMapping("/get-ticket/{ticketId}")
    public ResponseEntity<TicketResponse> getTicketByTicketId(@PathVariable("ticketId") String ticketId) {
        logger.info("TicketController - Inside getTicketByTicketId method");
        TicketResponse ticketResponse = ticketService.getTicketByTicketId(ticketId);
        return ResponseEntity.of(Optional.ofNullable(ticketResponse));
    }

    @GetMapping("get-status/{ticketId}")
    public ResponseEntity<TicketStatus> getStatusByTicketId(@PathVariable("ticketId") String ticketId) {
        logger.info("TicketController - Inside getStatusByTicketId method");
        TicketStatus ticketStatus = ticketService.getStatusByTicketId(ticketId);
        return ResponseEntity.of(Optional.ofNullable(ticketStatus));
    }

    @GetMapping("/getSeatDetails")
    public ResponseEntity<SeatDetails> getSeatDetailsByDate( @RequestParam(name = "busId") Integer busId ,@RequestParam(name = "bookingDate") String bookingDate) {
        logger.info("TicketController - Inside getSeatDetailsByDate method");
        SeatDetails seatDetails = ticketService.getSeatDetailsByDate(busId,LocalDate.parse(bookingDate));
        return ResponseEntity.of(Optional.ofNullable(seatDetails));
    }

}


