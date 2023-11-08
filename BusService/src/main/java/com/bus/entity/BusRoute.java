package com.bus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Entity(name = "bus_route")
@NoArgsConstructor
@AllArgsConstructor
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String routeId = String.valueOf(UUID.randomUUID());
    private String routeFrom;
    private String routeTo;
    private double distance;
    private double fare;

}