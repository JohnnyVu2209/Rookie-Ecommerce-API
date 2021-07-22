package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.dto.request.Category.CreateCategoryDTO;
import com.musical.instrument.ecommerce.dto.request.Category.UpdateCategoryDTO;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.exception.DeleteDataFailException;
import com.musical.instrument.ecommerce.exception.DuplicateException;
import com.musical.instrument.ecommerce.exception.UpdateDataFailException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.dto.request.Category.CategoryDTO;

public interface CategoryService {
	public Page<CategoryDTO> CategoryList(int pageNo, int pageSize,String sortDir);

	public CategoryDTO AddCategory(CreateCategoryDTO createCategoryDTO)
            throws CreateDataFailException, DuplicateException;

	public CategoryDTO UpdateCategory(Long id, UpdateCategoryDTO categoryDTO)
            throws UpdateDataFailException, DuplicateException;

	public boolean DeleteCategory(Long id) throws DeleteDataFailException;


}
