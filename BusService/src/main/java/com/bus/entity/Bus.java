package com.bus.entity;

import com.bus.enums.BusStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String busId;
    private Integer busNo;
    private String busName;
    private String source;
    private String destination;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer totalSeats;
    private Integer availableSeats;

    private List<String> availableDays;
    @Enumerated(EnumType.STRING)
    private BusStatus status;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "bus_id")
    private List<BusStop> haltStops;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "bus_id")
    private List<BusRoute> busRoutes;

}
