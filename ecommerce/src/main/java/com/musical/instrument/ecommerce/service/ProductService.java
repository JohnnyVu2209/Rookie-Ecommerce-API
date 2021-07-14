package com.musical.instrument.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.Product.UpdateProductDTO;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.exception.DeleteDataFailException;
import com.musical.instrument.ecommerce.exception.UpdateDataFailException;

public interface ProductService {
	public List<ProductDTO> ListProduct();

	public List<ProductDTO> FindProductByCategory(int cateId);

	public ProductDTO CreateProduct(Product product) throws CreateDataFailException;

	public ProductDTO UpdateProduct(int productId, UpdateProductDTO product) throws UpdateDataFailException;

	public Boolean DeleteProduct(int product) throws DeleteDataFailException;
}
