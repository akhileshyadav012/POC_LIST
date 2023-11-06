package com.payment.service.impl;

import com.payment.entity.OrderDetails;
import com.payment.exception.NotFoundException;
import com.payment.repository.OrderDetailsRepository;
import com.payment.request.CreateOrderRequest;
import com.payment.request.UpdateStatusRequest;
import com.payment.service.IOrderService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class OrderServiceImpl implements IOrderService {
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Value("${razor-pay.key-id}")
    private String key_id;
    @Value("${razor-pay.key-secret}")
    private String key_secret;
    @Override
    public OrderDetails createOrder(CreateOrderRequest request) throws RazorpayException {
        logger.info("OrderServiceImpl - Inside createOrder method");
        BigDecimal amount = request.getAmount();
        RazorpayClient client = new RazorpayClient(key_id, key_secret);
        JSONObject object = new JSONObject();
        object.put("amount", amount.multiply(BigDecimal.valueOf(100)));
        object.put("currency", "INR");
        object.put("receipt", "txn_235435");

        Order order = client.orders.create(object);
        System.out.println("order = " + order);

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(order.get("id"));
        orderDetails.setAmount(order.get("amount"));
        orderDetails.setCurrency(order.get("currency"));
        orderDetails.setStatus(order.get("status"));
        orderDetailsRepository.save(orderDetails);
        return orderDetails;

    }
    @Override
    public String updateOrder(UpdateStatusRequest request) {
        logger.info("OrderServiceImpl - Inside updateOrder method");
        Optional<OrderDetails> optionalOrderDetails = orderDetailsRepository.findByOrderId(request.getOrderId());
        if (optionalOrderDetails.isEmpty()){
            throw new NotFoundException("OrderDetails nhi  mila");
        }
        OrderDetails orderDetails = optionalOrderDetails.get();
        orderDetails.setSignature(request.getSignature());
        orderDetails.setStatus(request.getStatus());
        orderDetails.setPaymentId(request.getPaymentId());
        orderDetailsRepository.save(orderDetails);
        return "hello";
    }
}
