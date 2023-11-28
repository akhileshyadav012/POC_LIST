package com.bus.repository;

import com.bus.entity.SeatDetails;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SeatDetailsRepository extends JpaRepository<SeatDetails, Long> {

    Optional<SeatDetails> findByBookingDate(LocalDate date);
}
