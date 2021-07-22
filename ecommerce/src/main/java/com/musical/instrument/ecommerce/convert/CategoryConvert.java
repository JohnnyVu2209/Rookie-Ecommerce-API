package com.musical.instrument.ecommerce.convert;

import com.musical.instrument.ecommerce.dto.request.Category.CategoryChildDTO;
import com.musical.instrument.ecommerce.dto.request.Category.CreateCategoryDTO;
import com.musical.instrument.ecommerce.dto.request.Category.UpdateCategoryDTO;
import com.musical.instrument.ecommerce.dto.response.ErrorCode;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.dto.request.Category.CategoryDTO;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryConvert {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepository categoryRepository;

	public CategoryDTO ToDto(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryName(category.getName());
		Set<CategoryChildDTO> categories = category.getSubCategories().stream()
										   .filter(category1 -> category1.getIsDeleted() == false)
												   .map(category1 -> categoryChildDto(category1))
												   .collect(Collectors.toSet());
		categoryDTO.setCategoriesChildName(categories);

		return categoryDTO;
	}

	public Category ToEntity(CreateCategoryDTO categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getCategoryName());
		if(categoryDto.getCategoryParentName() != null){
			Category parent = categoryRepository.findByName(categoryDto.getCategoryParentName())
									  .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND));
			category.setParent(parent);
		}
		return category;
	}
	public CategoryChildDTO categoryChildDto(Category category){
		CategoryChildDTO childDTO = new CategoryChildDTO();
		if(category.getIsDeleted()==false)
			childDTO.setCategoryName(category.getName());
		return childDTO;
	}
	public Category ToEntity(UpdateCategoryDTO categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getCategoryName());
		if(categoryDto.getCategoryParentName() != null){
			Category parent = categoryRepository.findByName(categoryDto.getCategoryParentName())
												.orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND));
			category.setParent(parent);
		}
		return category;
	}
}
