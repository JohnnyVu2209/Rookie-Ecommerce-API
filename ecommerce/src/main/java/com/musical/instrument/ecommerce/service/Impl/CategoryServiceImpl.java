package com.musical.instrument.ecommerce.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.convert.CategoryConvert;
import com.musical.instrument.ecommerce.dto.CategoryDTO;
import com.musical.instrument.ecommerce.dto.Product.ProductDTO;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import com.musical.instrument.ecommerce.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	private CategoryConvert convert;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public List<CategoryDTO> CategoryList() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll().stream().map(category -> convert.ToDto(category))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO FindCatgory(int id) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category not found"));
		return convert.ToDto(category);
	}


	

}
