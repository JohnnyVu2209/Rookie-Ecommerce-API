package com.musical.instrument.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.dto.CategoryDTO;

public interface CategoryService {
	public List<CategoryDTO> CategoryList();

	public CategoryDTO FindCatgory(int id);

	
}
