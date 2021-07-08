package com.musical.instrument.ecommerce.service;

import java.util.List;

import com.musical.instrument.ecommerce.Entity.Category;

public interface CategoryService {
	public List<Category> CategoryList();
	
	public Category FindCatgory(int id);
}
