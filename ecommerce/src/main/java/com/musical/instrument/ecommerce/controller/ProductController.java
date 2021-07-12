package com.musical.instrument.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musical.instrument.ecommerce.dto.ProductDTO;
import com.musical.instrument.ecommerce.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public List<ProductDTO> getListProduct() {
		return productService.ListProduct();
	}

	@GetMapping("/{id}")
	public ProductDTO getProduct(@PathVariable("id") int productId) {
		return productService.FindProduct(productId);
	}

	@PostMapping("/add")
	public ProductDTO addNewProduct(@Valid @RequestBody ProductDTO dto) {
		return productService.CreateProduct(dto);
	}

	@PutMapping("/update/{id}")
	public ProductDTO updateProduct(@PathVariable("id") int productId, @Valid @RequestBody ProductDTO dto) {
		return productService.UpdateProduct(productId, dto);
	}

	@DeleteMapping("/delete/{id}")
	public void DeleteProduct(@PathVariable("id") int productId) {
		productService.DeleteProduct(productId);
	}
}
