package com.bus.repository;

import com.bus.entity.Bus;
import com.bus.enums.BusStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Integer> {

    Optional<Bus> findByBusId(Integer busId);
    @Transactional
    void deleteByBusId(Integer busId);
    Bus findByBusIdAndStatus(Integer busId, BusStatus status);


//    @Query("SELECT b.id FROM Bus b join BusRoute br on b.id = br.bus_id where br.route_from = "Airoli" and br.route_to = "Rabale" and b.status = "ACTIVE" ")
//    List<String> findBusIdBySourceAndDestination(String routeFrom, String routeTo);
}
