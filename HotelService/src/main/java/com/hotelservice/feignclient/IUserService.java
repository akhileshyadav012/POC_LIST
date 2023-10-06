package com.hotelservice.feignclient;

import com.hotelservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface IUserService {

    @GetMapping("/v1/users/id/{id}")
    User getUser(@PathVariable("id") String id);
}