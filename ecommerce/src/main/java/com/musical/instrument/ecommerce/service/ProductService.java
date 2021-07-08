package com.musical.instrument.ecommerce.service;

import java.util.List;

import com.musical.instrument.ecommerce.Entity.Product;

public interface ProductService {
	public List<Product> ListProduct();
	
	public List<Product> ListProduct(int cateId);
	
	public Product FindProduct(int proId);
	
	public Product CreateProduct(Product procduct);
	
	public Product EditProduct(Product product);
	
	public void DeleteProduct(int procduct);
}
