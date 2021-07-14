package com.musical.instrument.ecommerce.dto;

import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.Entity.Brand;

public class BrandDTO {

	private String brand_name;

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

}
