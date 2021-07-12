package com.musical.instrument.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.dto.BrandDTO;

public interface BrandService {
	public List<BrandDTO> brandList();

	public BrandDTO FindBrand(int brand_id);

}
