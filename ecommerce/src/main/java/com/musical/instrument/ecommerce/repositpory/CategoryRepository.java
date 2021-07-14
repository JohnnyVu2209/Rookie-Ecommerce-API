package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
