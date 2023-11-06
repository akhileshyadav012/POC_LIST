package com.payment.controller;

import com.payment.entity.OrderDetails;
import com.payment.request.CreateOrderRequest;
import com.payment.request.UpdateStatusRequest;
import com.payment.service.impl.OrderServiceImpl;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private static final Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping(path = "/create")
    public OrderDetails createOrder(@RequestBody CreateOrderRequest request) throws RazorpayException {
        logger.info("OrderController - Inside createOrder method");
        OrderDetails orderDetails = orderService.createOrder(request);
        return orderDetails;
    }

    @PostMapping("/update")
    public String updateOrder(@RequestBody UpdateStatusRequest request) {
        logger.info("OrderController - Inside updateOrder method");
        String string = orderService.updateOrder(request);
        return string;
    }
}
