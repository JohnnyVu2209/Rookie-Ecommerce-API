package com.musical.instrument.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
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
import com.musical.instrument.ecommerce.dto.Product.CreateProductDTO;
import com.musical.instrument.ecommerce.dto.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.Product.UpdateProductDTO;
import com.musical.instrument.ecommerce.dto.responsedto.ResponseDTO;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.exception.DeleteDataFailException;
import com.musical.instrument.ecommerce.exception.UpdateDataFailException;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;
import com.musical.instrument.ecommerce.service.ProductService;

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
	public ResponseEntity<ResponseDTO> getListProduct() {
		ResponseDTO response = new ResponseDTO();
		response.setData(productService.ListProduct());
		response.setSuccessCode("LOAD_LIST_SUCCESS");
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getProduct(@PathVariable("id") int productId) {
		ResponseDTO response = new ResponseDTO();
		try {
			Optional<Product> product = productRepository.findById(productId);
			ProductDTO productDTO = productConvert.ToDto(product.get());
			if (product.isPresent()) {
				response.setData(productDTO);
				response.setSuccessCode("PRODUCT_LOAD_SUCCESS");
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setErrorCode("PRODUCT_LOAD_FAIL");
			throw new DataNotFoundException("PRODUCT_LOAD_FAIL");
		}
		return ResponseEntity.ok().body(response);
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
		return ResponseEntity.ok().body(response);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateProduct(@PathVariable("id") int productId,
			@Valid @RequestBody UpdateProductDTO dto) throws UpdateDataFailException {
		ResponseDTO response = new ResponseDTO();
		try {
			ProductDTO productDTO = productService.UpdateProduct(productId, dto);
			response.setData(productDTO);
			response.setSuccessCode("UPDATE_PRODUCT_SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setErrorCode("UPDATE_PRODUCT_FAIL");
			throw new DataNotFoundException("UPDATE_PRODUCT_FAIL");
		}
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> DeleteProduct(@PathVariable("id") int productId) throws DeleteDataFailException {
		ResponseDTO response = new ResponseDTO();
		try {
			boolean isDeleted = productService.DeleteProduct(productId);
			response.setSuccessCode("PRODUCT_DELETED_SUCCESS");
		} catch (Exception e) {
			// TODO: handle exception
			response.setErrorCode("PRODUCT_DELETED_FAIL");
			throw new DeleteDataFailException("PRODUCT_DELETED_FAIL");
		}
		return ResponseEntity.ok().body(response);
	}
}
