package com.userservice.feignclient;

import com.userservice.entity.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface IRatingService {

    @GetMapping("/v1/ratings/users/{userId}")
    List<Rating> getRatings(@PathVariable("userId") String userId);

}
