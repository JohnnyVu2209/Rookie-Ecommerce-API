package com.musical.instrument.ecommerce.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.exception.Exception;
import com.musical.instrument.ecommerce.repositpory.BrandRepository;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;
import com.musical.instrument.ecommerce.service.CategoryService;
import com.musical.instrument.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public ProductServiceImpl(ProductRepository repository, BrandRepository brandRepository,
			CategoryRepository categoryRepository) {
		this.repository = repository;
		this.brandRepository = brandRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Product> ListProduct() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Product FindProduct(int proId) {
		// TODO Auto-generated method stub
		return repository.findById(proId).orElseThrow(() -> new Exception("Product not found"));
	}

	@Override
	public Product CreateProduct(Product product) {
		// TODO Auto-generated method stub
		return repository.save(product);
	}

	@Override
	public Product UpdateProduct(int productId, Product product) {
		// TODO Auto-generated method stub
		return repository.findById(productId).map(prod -> {
			prod.setProduct_name(product.getProduct_name());
			prod.setDescription(product.getDescription());
			prod.setPrice(product.getPrice());
			prod.setQuantity(product.getQuantity());
			prod.setCreate_date(product.getCreate_date());
			prod.setUpdate_date(product.getUpdate_date());
			prod.setBrand_id(product.getBrand_id());
			prod.setCategory_id(product.getCategory_id());
			return repository.save(prod);
		}).orElseThrow(() -> new Exception("Product not found"));
	}

	@Override
	public void DeleteProduct(int product) {
		// TODO Auto-generated method stub
		Product new_product = repository.findById(product).orElseThrow(() -> new Exception("Product not found"));
	}

}
