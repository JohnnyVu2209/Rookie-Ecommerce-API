package com.musical.instrument.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.dto.request.CategoryDTO;

@Service
public interface CategoryService {
	public Page<CategoryDTO> CategoryList();

	public CategoryDTO FindCatgory(Long id);

	
}
