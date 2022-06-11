package com.umang345.payment.service.api.controller;

import com.umang345.payment.service.api.entity.Payment;
import com.umang345.payment.service.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController
{
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment)
    {
         return paymentService.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryOrderById(@PathVariable int orderId)
    {
         return paymentService.findPaymentHistoryOrderById(orderId);
    }
}
