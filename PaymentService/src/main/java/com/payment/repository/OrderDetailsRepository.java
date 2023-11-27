package com.payment.repository;

import com.payment.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    Optional<OrderDetails> findByOrderId(String orderId);
}
