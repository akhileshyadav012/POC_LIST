package com.payment.service;

import com.payment.entity.OrderDetails;
import com.payment.request.CreateOrderRequest;
import com.payment.request.UpdateStatusRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.RequestBody;

public interface IOrderService {
    OrderDetails createOrder(CreateOrderRequest request) throws RazorpayException;
    String updateOrder(UpdateStatusRequest request);

}
