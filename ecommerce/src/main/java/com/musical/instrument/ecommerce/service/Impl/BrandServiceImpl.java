package com.musical.instrument.ecommerce.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.exception.Exception;
import com.musical.instrument.ecommerce.repositpory.BrandRepository;
import com.musical.instrument.ecommerce.service.BrandService;

public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	public BrandServiceImpl(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	@Override
	public List<Brand> brandList() {
		// TODO Auto-generated method stub
		return brandRepository.findAll();
	}

	@Override
	public Brand FindBrand(int brand_id) {
		// TODO Auto-generated method stub
		return brandRepository.findById(brand_id).orElseThrow(() -> new Exception("Brand not found"));
	}

}
