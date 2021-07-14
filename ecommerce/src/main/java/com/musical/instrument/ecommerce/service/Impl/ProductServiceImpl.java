package com.musical.instrument.ecommerce.service.Impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import com.musical.instrument.ecommerce.dto.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.Product.UpdateProductDTO;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.exception.DeleteDataFailException;
import com.musical.instrument.ecommerce.exception.UpdateDataFailException;
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
		List<Product> products = categoryRepository.findById(cateId).get().getProducts();
		return products.stream().map(product -> convert.ToDto(product)).collect(Collectors.toList());
	}

	@Override
	public ProductDTO CreateProduct(Product product) throws CreateDataFailException {
		// TODO Auto-generated method stub
		return convert.ToDto(repository.save(product));
	}

	@Override
	public ProductDTO UpdateProduct(int productId, UpdateProductDTO productdto) throws UpdateDataFailException {
		// TODO Auto-generated method stub
		Product oldProduct = repository.findById(productId)
				.orElseThrow(() -> new DataNotFoundException("PRODUCT_NOT_FOUND"));
		try {
			Product product = convert.ToEntity(productdto);
			oldProduct.setProduct_name(product.getProduct_name());
			oldProduct.setDescription(product.getDescription());
			oldProduct.setPrice(product.getPrice());
			oldProduct.setQuantity(product.getQuantity());
			oldProduct.setUpdate_date(new Date());
			oldProduct.setBrand(product.getBrand());
			oldProduct.setCategory(product.getCategory());
			convert.ToDto(repository.save(oldProduct));
		} catch (Exception e) {
			// TODO: handle exception
			throw new UpdateDataFailException("PRODUCT_UPDATED_FAIL");
		}
		return convert.ToDto(oldProduct);
	}

	@Override
	public Boolean DeleteProduct(int productId) throws DeleteDataFailException {
		// TODO Auto-generated method stub
		Product product = repository.findById(productId)
				.orElseThrow(() -> new DataNotFoundException("PRODUCT_NOT_FOUND"));
		try {
			product.setIsDeleted(true);
			repository.save(product);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DeleteDataFailException("PRODUCT_DELETED_FAIL");
		}

		return product.getIsDeleted();
	}

}
