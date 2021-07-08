package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musical.instrument.ecommerce.Entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

}
