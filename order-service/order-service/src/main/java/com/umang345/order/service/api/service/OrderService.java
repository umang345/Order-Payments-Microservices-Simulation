package com.umang345.order.service.api.service;

import com.umang345.order.service.api.common.Payment;
import com.umang345.order.service.api.common.TransactionRequest;
import com.umang345.order.service.api.common.TransactionResponse;
import com.umang345.order.service.api.entity.Order;
import com.umang345.order.service.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request)
    {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice()*order.getQty());

        Payment paymentResponse =
                restTemplate.postForObject("http://localhost:9191/payment/doPayment",payment,Payment.class);

        String response = "";

        response =
                paymentResponse.getPaymentStatus().equals("success")?
                        "Payment successful and Order is placed"
                        :
                        "Payment failed. Redirecting to cart";

         repository.save(order);
         return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),response);
    }
}
