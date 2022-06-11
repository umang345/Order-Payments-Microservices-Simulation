package com.umang345.payment.service.api.service;

import com.umang345.payment.service.api.entity.Payment;
import com.umang345.payment.service.api.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService
{
    @Autowired
    private PaymentRepository repository;

    public Payment doPayment(Payment payment)
    {
         payment.setPaymentStatus(paymentProcessing());
         payment.setTransactionId(UUID.randomUUID().toString());
         return repository.save(payment);
    }

    public String paymentProcessing()
    {
        /* This API call should be to a 3rd party Payment Gateway
         *  Using Random generated response just for simulation purpose
         */
        return new Random().nextBoolean()? "success":"false";
    }

    public Payment findPaymentHistoryOrderById(int orderId)
    {
        return repository.findByOrderId(orderId);
    }
}
