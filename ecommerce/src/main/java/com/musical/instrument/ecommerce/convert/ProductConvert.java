package com.musical.instrument.ecommerce.convert;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.BrandDTO;
import com.musical.instrument.ecommerce.dto.CategoryDTO;
import com.musical.instrument.ecommerce.dto.Product.CreateProductDTO;
import com.musical.instrument.ecommerce.dto.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.Product.UpdateProductDTO;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.repositpory.BrandRepository;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;

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
		productDto.setId_brand(product.getBrand().getId());
		return productDto;
	}

	public Product ToEntity(ProductDTO productDto) throws ParseException {
		Product product = mapper.map(productDto, Product.class);
		return product;
	}
	public Product ToEntity(CreateProductDTO createProductDTO) {
		Product product = mapper.map(createProductDTO, Product.class);
		Optional<Category> category = categoryRepository.findById(createProductDTO.getId_category());
		if(category.isEmpty()) {
			throw new DataNotFoundException("CATEGORY_NOT_FOUND");
		}
		Optional<Brand> brand = brandRepository.findById(createProductDTO.getId_brand());
		if(brand.isEmpty()) {
			throw new DataNotFoundException("BRAND_NOT_FOUND");
		}
		product.setBrand(brand.get());
		product.setCategory(category.get());
		return product;
	}
	public Product ToEntity(UpdateProductDTO updateProductDTO) {
		Product product = mapper.map(updateProductDTO, Product.class);
		Optional<Category> category = categoryRepository.findById(updateProductDTO.getId_category());
		if(category.isEmpty()) {
			throw new DataNotFoundException("CATEGORY_NOT_FOUND");
		}
		Optional<Brand> brand = brandRepository.findById(updateProductDTO.getId_brand());
		if(brand.isEmpty()) {
			throw new DataNotFoundException("BRAND_NOT_FOUND");
		}
		product.setBrand(brand.get());
		product.setCategory(category.get());
		return product;
	}
}
