package com.ticket.external.impl;

import com.ticket.entity.OrderDetails;
import com.ticket.request.CreateOrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentServiceFeignClient {

    @PostMapping("/v1/orders/create")
    OrderDetails createOrder(CreateOrderRequest request);
}
