package com.musical.instrument.ecommerce.service;

import org.springframework.data.domain.Page;

import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.request.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.UpdateProductDTO;
import com.musical.instrument.ecommerce.exception.*;


public interface ProductService {
	public Page<ProductDTO> ListProduct(int PageNo, int pageSize, String sortField, String sortDir);

	public Page<ProductDTO> FindProductByCategory(Long cateId);

	public ProductDTO CreateProduct(Product product) throws CreateDataFailException;

	public ProductDTO UpdateProduct(Long productId, Product product) throws UpdateDataFailException;

	public Boolean DeleteProduct(Long product) throws DeleteDataFailException;
}
