package com.musical.instrument.ecommerce.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.convert.ProductConvert;
import com.musical.instrument.ecommerce.dto.BrandDTO;
import com.musical.instrument.ecommerce.dto.CategoryDTO;
import com.musical.instrument.ecommerce.dto.ProductDTO;
import com.musical.instrument.ecommerce.exception.ApiException;
import com.musical.instrument.ecommerce.repositpory.BrandRepository;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;
import com.musical.instrument.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	private ProductConvert convert;

	public ProductServiceImpl(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<ProductDTO> ListProduct() {
		// TODO Auto-generated method stub
		List<Product> products = repository.findAll();
		return products.stream().map(product -> convert.ToDto(product)).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> FindProductByCategory(int cateId) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		repository.findByCategoryId(cateId).forEach(products::add);
		return products.stream().map(product -> convert.ToDto(product)).collect(Collectors.toList());

	}

	@Override
	public ProductDTO FindProduct(int proId) {
		// TODO Auto-generated method stub
		Product product = repository.findById(proId).orElseThrow(() -> new ApiException("Product Id Not Found"));
		return convert.ToDto(product);
	}

	@Override
	public ProductDTO CreateProduct(ProductDTO productDto) {
		// TODO Auto-generated method stub
		Product product = convert.ToEntity(productDto);
		Date date = new Date();
		product.setCreate_date(date);
		if (categoryRepository.findById(product.getCategory().getId()) == null) {
			throw new ApiException("Category not exists");
		}
		if (brandRepository.findById(product.getBrand().getId()) == null) {
			throw new ApiException("Brand not exists");
		}
		return convert.ToDto(repository.save(product));
	}

	@Override
	public ProductDTO UpdateProduct(int productId, ProductDTO productdto) {
		// TODO Auto-generated method stub
		Product product = convert.ToEntity(productdto);
		return repository.findById(productId).map(prod -> {
			prod.setProduct_name(product.getProduct_name());
			prod.setDescription(product.getDescription());
			prod.setPrice(product.getPrice());
			prod.setQuantity(product.getQuantity());
			prod.setUpdate_date(new Date());
			if (categoryRepository.findById(product.getCategory().getId()) == null) {
				throw new ApiException("Category not exists");
			}
			if (brandRepository.findById(product.getBrand().getId()) == null) {
				throw new ApiException("Brand not exists");
			}
			prod.setBrand(product.getBrand());
			prod.setCategory(product.getCategory());
			return convert.ToDto(repository.save(prod));
		}).orElseThrow(() -> new ApiException("Product not found"));
	}

	@Override
	public void DeleteProduct(int product) {
		// TODO Auto-generated method stub
		repository.deleteById(product);
	}

}
