package com.umang345.order.service.api.service;

import com.umang345.order.service.api.common.Payment;
import com.umang345.order.service.api.common.TransactionRequest;
import com.umang345.order.service.api.common.TransactionResponse;
import com.umang345.order.service.api.entity.Order;
import com.umang345.order.service.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService
{
    @Autowired
    private OrderRepository repository;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request)
    {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice()*order.getQty());

        Payment paymentResponse =
                restTemplate.postForObject(ENDPOINT_URL,payment,Payment.class);

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
