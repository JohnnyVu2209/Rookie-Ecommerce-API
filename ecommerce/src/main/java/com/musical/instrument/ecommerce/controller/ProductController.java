package com.musical.instrument.ecommerce.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.musical.instrument.ecommerce.dto.request.Product.CreateProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.UpdateProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.convert.ProductConvert;
import com.musical.instrument.ecommerce.dto.request.Product.*;
import com.musical.instrument.ecommerce.dto.response.*;
import com.musical.instrument.ecommerce.exception.*;
import com.musical.instrument.ecommerce.service.*;
import com.musical.instrument.ecommerce.repositpory.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductConvert productConvert;

	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ResponseDTO> getListProduct() {
		ResponseDTO response = new ResponseDTO();
		response.setData(productService.ListProduct());
		response.setSuccessCode("LOAD_LIST_SUCCESS");
		return ResponseEntity.ok().body(response);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getProduct(@PathVariable("id") Long productId) {
		ResponseDTO response = new ResponseDTO();
		try {
			Product product = productRepository.findById(productId)
											   .orElseGet(() -> {
													response.setErrorCode("PRODUCT_NOT_FOUND");
												  	throw new DataNotFoundException("PRODUCT_NOT_FOUND");
											   });
				ProductDTO productDTO = productConvert.ToDto(product);
				response.setData(productDTO);
				response.setSuccessCode("PRODUCT_LOAD_SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setErrorCode("PRODUCT_LOAD_FAIL");
			throw new DataNotFoundException("PRODUCT_LOAD_FAIL");
		}
		return ok().body(response);
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseDTO> addNewProduct(@Valid @RequestBody CreateProductDTO dto)
			throws CreateDataFailException {
		ResponseDTO response = new ResponseDTO();
		try {
			ProductDTO productDTO = productService.CreateProduct(productConvert.ToEntity(dto));
			response.setData(productDTO);
			response.setSuccessCode("CREATE_PRODUCT_SUCCESS");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.setErrorCode("CREATE_PRODUCT_FAIL");
			throw new CreateDataFailException("CREATE_PRODUCT_FAIL");
		}
		return ok().body(response);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateProduct(@PathVariable("id") Long productId,
			@Valid @RequestBody UpdateProductDTO dto) throws UpdateDataFailException {
		ResponseDTO response = new ResponseDTO();
		try {
			Product product = productConvert.ToEntity(dto);
			ProductDTO productDTO = productService.UpdateProduct(productId, product);
			response.setData(productDTO);
			response.setSuccessCode("UPDATE_PRODUCT_SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setErrorCode("UPDATE_PRODUCT_FAIL");
			throw new DataNotFoundException("UPDATE_PRODUCT_FAIL");
		}
		return ok().body(response);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> DeleteProduct(@PathVariable("id") Long productId) throws DeleteDataFailException {
		ResponseDTO response = new ResponseDTO();
		try {
			boolean isDeleted = productService.DeleteProduct(productId);
			response.setSuccessCode("PRODUCT_DELETED_SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setErrorCode("PRODUCT_DELETED_FAIL");
			throw new DeleteDataFailException("PRODUCT_DELETED_FAIL");
		}
		return ok().body(response);
	}
}
