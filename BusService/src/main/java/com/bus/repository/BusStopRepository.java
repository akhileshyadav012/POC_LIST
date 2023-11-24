package com.bus.repository;

import com.bus.entity.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BusStopRepository extends JpaRepository<BusStop, Integer> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from bus_stop bs where bs.bus_id = :bus_Id")
    void deleteBusStops(@Param("bus_Id") Integer bus_Id);
}
