package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musical.instrument.ecommerce.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
