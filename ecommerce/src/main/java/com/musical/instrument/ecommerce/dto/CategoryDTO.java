package com.musical.instrument.ecommerce.dto;

import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.Entity.Category;

public class CategoryDTO {

	private String Category_name;

	public String getCategory_name() {
		return Category_name;
	}

	public void setCategory_name(String category_name) {
		Category_name = category_name;
	}

}
