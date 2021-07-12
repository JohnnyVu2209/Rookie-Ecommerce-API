package com.musical.instrument.ecommerce.dto;

import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.Entity.Brand;

public class BrandDTO {

	private int id_brand;

	@NotBlank(message = "Vui lòng nhập tên thương hiệu")
	private String brand_name;

	public BrandDTO() {
	}

	public BrandDTO(Brand brand) {
		this.id_brand = brand.getId();
		this.brand_name = brand.getBrand_name();
	}
	
	public BrandDTO(int id_brand, @NotBlank(message = "Vui lòng nhập tên thương hiệu") String brand_name) {
		this.id_brand = id_brand;
		this.brand_name = brand_name;
	}

	public int getId_brand() {
		return id_brand;
	}

	public void setId_brand(int id_brand) {
		this.id_brand = id_brand;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

}
