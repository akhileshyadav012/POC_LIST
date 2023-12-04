package com.gateway.external.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE")
public interface AuthControllerFeignClient {

    @GetMapping("/v1/auth/validate")
    Boolean validateToken(@RequestParam(name = "token") String token);
}
