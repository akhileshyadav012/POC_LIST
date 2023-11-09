package com.bus.repository;

import com.bus.entity.BusRoute;
import com.bus.response.GetBusesQueryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRouteRepository extends JpaRepository<BusRoute,Integer> {
    @Query(nativeQuery = true,value = "select bus_id,distance,fare from bus_route br where br.route_from = :source and br.route_to = :destination")
    List<GetBusesQueryResponse> getBusesIdsFromSourceAndDestination(@Param("source") String source,@Param("destination") String destination);
}
