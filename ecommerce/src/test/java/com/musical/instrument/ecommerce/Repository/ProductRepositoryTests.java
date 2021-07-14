package com.musical.instrument.ecommerce.Repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.repositpory.BrandRepository;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;

@SpringBootTest
public class ProductRepositoryTests {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void TestCreateProductSuccess() {
		Product product = new Product();
		product.setProduct_name("cc 1011");
		product.setQuantity(10);
		product.setPrice(1000000);
		Optional<Brand> brand = brandRepository.findById(1);
		product.setBrand(brand.get());
		Optional<Category> category = categoryRepository.findById(1);
		product.setCategory(category.get());
		assertNotNull(productRepository.save(product));	
	}

}
