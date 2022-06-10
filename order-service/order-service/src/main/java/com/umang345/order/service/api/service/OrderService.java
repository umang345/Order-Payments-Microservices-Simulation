package com.umang345.order.service.api.service;

import com.umang345.order.service.api.entity.Order;
import com.umang345.order.service.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository repository;

    public Order saveOrder(Order order)
    {
         return repository.save(order);
    }
}
