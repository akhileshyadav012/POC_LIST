package com.payment.service;

import com.payment.entity.OrderDetails;
import com.payment.request.CreateOrderRequest;
import com.razorpay.RazorpayException;

public interface IOrderService {
    OrderDetails createOrder(CreateOrderRequest request) throws RazorpayException;
//    String updateOrder(UpdateStatusRequest request);

}
