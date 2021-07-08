package com.musical.instrument.ecommerce.service;

import java.util.List;

import com.musical.instrument.ecommerce.Entity.Product;

public interface ProductService {
	public List<Product> ListProduct();
	
	public Product FindProduct(int proId);
	
	public Product CreateProduct(Product product);
	
	public Product UpdateProduct(int productId,Product product);
	
	public void DeleteProduct(int product);
}
