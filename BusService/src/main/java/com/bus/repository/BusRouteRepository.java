package com.bus.repository;

import com.bus.entity.BusRoute;
import com.bus.response.GetBusesQueryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BusRouteRepository extends JpaRepository<BusRoute, Integer> {
    @Query(nativeQuery = true, value = "select bus_id,distance,fare from bus_route br where br.route_from = :route_from and br.route_to = :route_to")
    List<GetBusesQueryResponse> getBusesIdsFromSourceAndDestination(@Param("route_from") String route_from, @Param("route_to") String route_to);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from bus_route bs where bs.bus_id = :bus_Id")
    void deleteBusRoute(@Param("bus_Id") Integer bus_Id);
}