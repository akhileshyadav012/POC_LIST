package com.ticket.controller;

import com.ticket.request.TicketRequest;
import com.ticket.response.TicketResponse;
import com.ticket.service.ITicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}


