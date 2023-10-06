package com.hotelservice.feignclient;

import com.hotelservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface IRatingService {

    @GetMapping("/v1/ratings/hotels/{id}")
    List<Rating> getRatings(@PathVariable("id") String id);
}
