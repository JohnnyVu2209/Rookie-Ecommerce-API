package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musical.instrument.ecommerce.Entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
