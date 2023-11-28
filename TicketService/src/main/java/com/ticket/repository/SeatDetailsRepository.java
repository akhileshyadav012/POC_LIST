package com.ticket.repository;

import com.ticket.entity.SeatDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SeatDetailsRepository extends JpaRepository<SeatDetails, Long> {

    Optional<SeatDetails> findByBusIdAndBookingDate(Integer busId,LocalDate date);
}
