package com.musical.instrument.ecommerce.convert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.dto.CategoryDTO;
import com.musical.instrument.ecommerce.dto.ProductDTO;

@Component
public class CategoryConvert {

	@Autowired
	private ModelMapper mapper;

	public CategoryDTO ToDto(Category category) {
		CategoryDTO productDto = mapper.map(category, CategoryDTO.class);
		return productDto;
	}

	public Category ToEntity(CategoryDTO categoryDto) throws ParseException {
		Category category = mapper.map(categoryDto, Category.class);
		return category;
	}
}
