package com.umang345.order.service.api.common;

import com.umang345.order.service.api.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest
{
    private Order order;
    private Payment payment;
}
