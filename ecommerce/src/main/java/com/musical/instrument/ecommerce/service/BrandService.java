package com.musical.instrument.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.dto.request.BrandDTO;

@Service
public interface BrandService {
	public Page<BrandDTO> brandList();

	public BrandDTO FindBrand(Long brand_id);

}
