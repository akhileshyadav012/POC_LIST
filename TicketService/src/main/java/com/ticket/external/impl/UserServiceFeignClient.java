package com.ticket.external.impl;

import com.ticket.external.response.UserResponse;
import com.ticket.interceptor.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", configuration = FeignConfig.class)
public interface UserServiceFeignClient {
    @GetMapping("/v1/users/{userId}")
    UserResponse getUserById(@PathVariable("userId") String userId);
    @GetMapping("/v1/users/demo")
    String demo();
    @GetMapping("/v1/users/logged-in-user")
    UserResponse getLoggedInUser();
}
