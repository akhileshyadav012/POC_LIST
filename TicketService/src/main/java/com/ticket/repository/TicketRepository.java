package com.ticket.repository;

import com.ticket.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    Optional<Ticket> findByTicketId(String ticketId);
}
