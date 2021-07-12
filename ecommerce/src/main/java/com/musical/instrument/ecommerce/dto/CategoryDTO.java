package com.musical.instrument.ecommerce.dto;

import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.Entity.Category;

public class CategoryDTO {

	private int id_category;

	@NotBlank(message = "Vui lòng nhập tên danh mục")
	private String Category_name;

	public CategoryDTO() {
	}
	
	public CategoryDTO(Category category) {
		this.id_category = category.getId();
		this.Category_name = category.getCategory_name();
	}

	public CategoryDTO(int id, @NotBlank(message = "Vui lòng nhập tên danh mục") String category_name) {
		this.id_category = id;
		Category_name = category_name;
	}

	public int getId() {
		return id_category;
	}

	public void setId(int id) {
		this.id_category = id;
	}

	public String getCategory_name() {
		return Category_name;
	}

	public void setCategory_name(String category_name) {
		Category_name = category_name;
	}

}
