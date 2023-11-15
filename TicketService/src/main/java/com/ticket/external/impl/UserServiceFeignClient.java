package com.ticket.external.impl;

import com.ticket.external.entity.User;
import com.ticket.external.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceFeignClient {
    @GetMapping("/v1/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId);

    @GetMapping("/v1/users")
    List<User> getAllUser();

    @GetMapping("/v1/users/demo")
    String demo();
}
