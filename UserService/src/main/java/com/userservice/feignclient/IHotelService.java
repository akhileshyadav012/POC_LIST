package com.userservice.feignclient;

import com.userservice.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface IHotelService {

    @GetMapping("/v1/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
}


