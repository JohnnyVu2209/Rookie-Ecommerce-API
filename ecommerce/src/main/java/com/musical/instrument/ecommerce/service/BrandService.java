package com.musical.instrument.ecommerce.service;

import java.util.List;

import com.musical.instrument.ecommerce.Entity.Brand;

public interface BrandService {
	public List<Brand> brandList();
	
	public Brand FindBrand(int brand_id);
}
