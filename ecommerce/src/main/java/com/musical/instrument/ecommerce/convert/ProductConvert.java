package com.musical.instrument.ecommerce.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.request.Product.CreateProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.UpdateProductDTO;
import com.musical.instrument.ecommerce.repositpory.*;

@Component
public class ProductConvert {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BrandRepository brandRepository;

	public ProductDTO ToDto(Product product) {
		ProductDTO productDto = mapper.map(product, ProductDTO.class);
		productDto.setId_category(product.getCategory().getId());
		productDto.setId_brand(product.getBrand().getId_brand());
		return productDto;
	}

	public Product ToEntity(CreateProductDTO createProductDTO) {
		Product product = mapper.map(createProductDTO, Product.class);
		product.setCategory(categoryRepository.findById(createProductDTO.getId_category()).get());
		product.setBrand(brandRepository.findById(createProductDTO.getId_brand()).get());
		return product;
	}

	public Product ToEntity(UpdateProductDTO updateProductDTO) {
		Product product = mapper.map(updateProductDTO, Product.class);
		product.setCategory(categoryRepository.findById(updateProductDTO.getId_category()).get());
		product.setBrand(brandRepository.findById(updateProductDTO.getId_brand()).get());
		return product;
	}
}
