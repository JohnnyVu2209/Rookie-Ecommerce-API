package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musical.instrument.ecommerce.Entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
