package com.musical.instrument.ecommerce.repositpory;

import java.awt.print.Pageable;
import java.util.List;

import com.musical.instrument.ecommerce.Entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.musical.instrument.ecommerce.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
		public Page<Product> findByCategory(Category category, PageRequest pageRequest);
}
