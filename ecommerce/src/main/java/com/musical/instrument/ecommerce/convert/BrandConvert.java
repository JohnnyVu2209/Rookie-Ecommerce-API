package com.musical.instrument.ecommerce.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.dto.request.BrandDTO;
@Component
public class BrandConvert {
	@Autowired
	private ModelMapper mapper;
	
	public BrandDTO ToDto(Brand brand) {
		BrandDTO brandDTO = mapper.map(brand, BrandDTO.class);
		return brandDTO;
	}

	public Brand ToEntity(BrandDTO brandDTO) throws ParseException {
		Brand brand = mapper.map(brandDTO, Brand.class);
		return brand;
	}
}
