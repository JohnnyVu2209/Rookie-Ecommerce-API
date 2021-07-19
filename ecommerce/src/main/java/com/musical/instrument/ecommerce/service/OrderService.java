package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.Entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public List<Order> ListOrder();
}
