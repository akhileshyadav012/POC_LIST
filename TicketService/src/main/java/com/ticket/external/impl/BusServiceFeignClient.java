package com.ticket.external.impl;

import com.ticket.external.response.BusResponse;
import com.ticket.interceptor.FeignConfig;
import com.ticket.interceptor.FeignConfig2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BUS-SERVICE", configuration = FeignConfig2.class)
public interface BusServiceFeignClient {

    @GetMapping("/v1/bus/{busId}")
    public BusResponse getBusById(@PathVariable("busId") Integer busId);
}
