package com.musical.instrument.ecommerce.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.BrandDTO;
import com.musical.instrument.ecommerce.dto.CategoryDTO;
import com.musical.instrument.ecommerce.dto.ProductDTO;

@Component
public class ProductConvert {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private BrandConvert brandConvert;

	@Autowired
	private CategoryConvert categoryConvert;

	public ProductDTO ToDto(Product product) {
		ProductDTO productDto = mapper.map(product, ProductDTO.class);
		if (product.getId() != 0) {
			productDto.setCategoryDTO(new CategoryDTO(product.getCategory()));
			productDto.setBrandDTO(new BrandDTO(product.getBrand()));
		}
		return productDto;
	}

	public Product ToEntity(ProductDTO productDto) throws ParseException {
		Product product = mapper.map(productDto, Product.class);
		product.setBrand(brandConvert.ToEntity(productDto.getBrandDTO()));
		product.setCategory(categoryConvert.ToEntity(productDto.getCategoryDTO()));
		return product;
	}
}
