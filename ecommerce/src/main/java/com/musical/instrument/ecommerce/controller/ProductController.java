package com.musical.instrument.ecommerce.controller;

import java.util.Optional;

import javax.validation.Valid;

import com.musical.instrument.ecommerce.dto.request.Product.CreateProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.UpdateProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/page/{PageNo}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ResponseDTO> getListProduct(@PathVariable("PageNo") int pageNo,
													 @RequestParam("sortField") String sortField,
													@RequestParam("sortDir") String sortDir) throws Exception{
		ResponseDTO response = new ResponseDTO();
		try {
			int pageSize = 5;
			response.setData(productService.ListProduct(pageNo,pageSize,sortField,sortDir));
			response.setSuccessCode(SuccessCode.PRODUCT_LIST_LOAD_SUCCESS);
		}catch (Exception e){
			throw new LoadDataFailException(ErrorCode.ERR_PRODUCT_LIST_LOAD_FAIL);
		}
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getProduct(@PathVariable("id") Long productId) {
		ResponseDTO response = new ResponseDTO();
		try {
			Product product = productRepository.findById(productId)
											   .orElseThrow(()->new DataNotFoundException(ErrorCode.ERR_PRODUCT_NOT_FOUND));
				ProductDTO productDTO = productConvert.ToDto(product);
				response.setData(productDTO);
				response.setSuccessCode(SuccessCode.PRODUCT_LOAD_SUCCESS);
		} catch (Exception e) {
			throw new DataNotFoundException(ErrorCode.ERR_PRODUCT_LOAD_FAIL);
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
			response.setSuccessCode(SuccessCode.CREATE_PRODUCT_SUCCESS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new CreateDataFailException(ErrorCode.ERR_CREATE_PRODUCT_FAIL);
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
			response.setSuccessCode(SuccessCode.UPDATE_PRODUCT_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DataNotFoundException(ErrorCode.ERR_UPDATE_PRODUCT_FAIL);
		}
		return ok().body(response);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseDTO> DeleteProduct(@PathVariable("id") Long productId) throws DeleteDataFailException {
		ResponseDTO response = new ResponseDTO();
		try {
			boolean isDeleted = productService.DeleteProduct(productId);
			response.setSuccessCode(SuccessCode.DELETE_PRODUCT_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			throw new DeleteDataFailException(ErrorCode.ERR_PRODUCT_DELETED_FAIL);
		}
		return ok().body(response);
	}
}
