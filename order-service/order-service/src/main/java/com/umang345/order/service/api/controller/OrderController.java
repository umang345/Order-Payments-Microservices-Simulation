package com.umang345.order.service.api.controller;

import com.umang345.order.service.api.common.Payment;
import com.umang345.order.service.api.common.TransactionRequest;
import com.umang345.order.service.api.common.TransactionResponse;
import com.umang345.order.service.api.entity.Order;
import com.umang345.order.service.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request)
    {
          return orderService.saveOrder(request);
    }
}
