package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musical.instrument.ecommerce.Entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer>{

}
