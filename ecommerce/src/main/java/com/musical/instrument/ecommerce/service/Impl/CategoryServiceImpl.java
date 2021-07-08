package com.musical.instrument.ecommerce.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.exception.Exception;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import com.musical.instrument.ecommerce.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<Category> CategoryList() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category FindCatgory(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));

	}

}
