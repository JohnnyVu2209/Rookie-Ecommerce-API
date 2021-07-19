package com.musical.instrument.ecommerce.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.convert.*;
import com.musical.instrument.ecommerce.dto.request.CategoryDTO;
import com.musical.instrument.ecommerce.exception.*;
import com.musical.instrument.ecommerce.repositpory.*;
import com.musical.instrument.ecommerce.service.CategoryService;


public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryConvert convert;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		categoryRepository = categoryRepository;
	}

	@Override
	public Page<CategoryDTO> CategoryList() {
		// TODO Auto-generated method stub
		PageRequest pageRequest= PageRequest.of(0, 2, Sort.by("name").descending());
		Page<Category> categories = categoryRepository.findAll(pageRequest);
		return categories.map(category -> convert.ToDto(category));
	}

	@Override
	public CategoryDTO FindCatgory(Long id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category not found"));
		return convert.ToDto(category);
	}


	

}
