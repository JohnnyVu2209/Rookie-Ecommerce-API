package com.musical.instrument.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.ProductDTO;

public interface ProductService {
	public List<ProductDTO> ListProduct();

	public ProductDTO FindProduct(int proId);

	public List<ProductDTO> FindProductByCategory(int cateId);

	public ProductDTO CreateProduct(ProductDTO product);

	public ProductDTO UpdateProduct(int productId, ProductDTO product);

	public void DeleteProduct(int product);
}
