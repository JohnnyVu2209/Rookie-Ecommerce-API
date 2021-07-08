package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musical.instrument.ecommerce.Entity.Product_rate;
import com.musical.instrument.ecommerce.composite.ProductRatingID;

public interface ProductRateRepository extends JpaRepository<Product_rate, ProductRatingID> {

}
