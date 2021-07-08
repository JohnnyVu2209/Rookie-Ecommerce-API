package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musical.instrument.ecommerce.Entity.Product_rate;
import com.musical.instrument.ecommerce.composite.ProductRatingID;

@Repository
public interface ProductRateRepository extends JpaRepository<Product_rate, ProductRatingID> {

}
