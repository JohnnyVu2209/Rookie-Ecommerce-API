package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musical.instrument.ecommerce.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
