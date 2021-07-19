package com.musical.instrument.ecommerce.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.convert.BrandConvert;
import com.musical.instrument.ecommerce.dto.request.BrandDTO;
import com.musical.instrument.ecommerce.exception.*;
import com.musical.instrument.ecommerce.repositpory.*;
import com.musical.instrument.ecommerce.service.*;


public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private BrandConvert brandConvert;

	public BrandServiceImpl(BrandRepository repository){
		 brandRepository = repository;
	}

	@Override
	public Page<BrandDTO> brandList() {
		// TODO Auto-generated method stub
		PageRequest pageRequest= PageRequest.of(0, 2, Sort.by("name").descending());
		Page<Brand> brandList = brandRepository.findAll(pageRequest);
		return brandList.map(brand -> brandConvert.ToDto(brand));
	}

	@Override
	public BrandDTO FindBrand(Long brand_id) {
		// TODO Auto-generated method stub
		Brand brand = brandRepository.findById(brand_id).orElseThrow(() -> new DataNotFoundException("Brand not found"));
		return brandConvert.ToDto(brand);
	}

}
