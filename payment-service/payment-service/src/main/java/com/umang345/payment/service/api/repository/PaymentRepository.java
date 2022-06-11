package com.umang345.payment.service.api.repository;

import com.umang345.payment.service.api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>
{

    Payment findByOrderId(int orderId);
}
