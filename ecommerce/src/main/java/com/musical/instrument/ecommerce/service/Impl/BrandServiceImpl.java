package com.musical.instrument.ecommerce.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.convert.BrandConvert;
import com.musical.instrument.ecommerce.dto.BrandDTO;
import com.musical.instrument.ecommerce.dto.CategoryDTO;
import com.musical.instrument.ecommerce.dto.Product.ProductDTO;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.repositpory.BrandRepository;
import com.musical.instrument.ecommerce.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	private BrandConvert brandConvert;

	public BrandServiceImpl(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}


	@Override
	public List<BrandDTO> brandList() {
		// TODO Auto-generated method stub
		return brandRepository.findAll().stream().map(category -> brandConvert.ToDto(category)).collect(Collectors.toList());
	}

	@Override
	public BrandDTO FindBrand(int brand_id) {
		// TODO Auto-generated method stub
		Brand brand = brandRepository.findById(brand_id).orElseThrow(() -> new DataNotFoundException("Brand not found"));
		return brandConvert.ToDto(brand);
	}

}
