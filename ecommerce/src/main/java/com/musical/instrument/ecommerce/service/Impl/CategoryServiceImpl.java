package com.musical.instrument.ecommerce.service.Impl;

import com.musical.instrument.ecommerce.dto.request.Category.CreateCategoryDTO;
import com.musical.instrument.ecommerce.dto.request.Category.UpdateCategoryDTO;
import com.musical.instrument.ecommerce.dto.response.ErrorCode;
import com.musical.instrument.ecommerce.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.convert.*;
import com.musical.instrument.ecommerce.dto.request.Category.CategoryDTO;
import com.musical.instrument.ecommerce.repositpory.*;
import com.musical.instrument.ecommerce.service.CategoryService;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryConvert convert;

	@Override
	public Page<CategoryDTO> CategoryList(int pageNo, int pageSize, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("name").ascending() :
				Sort.by("name").descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		Page<Category> categories = categoryRepository.findAllParent(pageable);


		return categories.map(category -> convert.ToDto(category));
	}

	@Override
	public CategoryDTO AddCategory(CreateCategoryDTO createCategoryDTO)
			throws CreateDataFailException, DuplicateException {
		CategoryDTO categoryDTO;
		try {
			Category category = convert.ToEntity(createCategoryDTO);
			Category newCate;
			if(categoryRepository.existsByName(category.getName())){
				Optional<Category> optional = categoryRepository.findByName(category.getName());
				if (optional.get().getIsDeleted()){
					optional.get().setIsDeleted(false);
					newCate = categoryRepository.save(optional.get());
					categoryDTO = convert.ToDto(newCate);
				}else {
					throw new DuplicateException(ErrorCode.ERR_CATEGORY_ALREADY_EXISTS);
				}
			}else {
				newCate = categoryRepository.save(category);
				categoryDTO = convert.ToDto(newCate);
			}
		}catch (DuplicateException e){
			throw new DuplicateException(ErrorCode.ERR_CATEGORY_ALREADY_EXISTS);
		}
		catch (Exception e){
			throw new CreateDataFailException(ErrorCode.ERR_CREATE_CATEGORY_FAIL);
		}
		return categoryDTO;
	}

	@Override
	public CategoryDTO UpdateCategory(Long id, UpdateCategoryDTO updateCategoryDTO)
			throws UpdateDataFailException, DuplicateException {
		CategoryDTO categoryDTO;
		try {
			Optional<Category> category = categoryRepository.findById(id);
			if(category.isEmpty())
				throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);

			Category updateCate = convert.ToEntity(updateCategoryDTO);
			if(categoryRepository.existsByName(updateCate.getName())){
				throw new DuplicateException(ErrorCode.ERR_CATEGORY_ALREADY_EXISTS);
			}
			category.get().setName(updateCate.getName());
			category.get().setParent(updateCate.getParent());
			Category updatedCate = categoryRepository.save(category.get());
			categoryDTO = convert.ToDto(updatedCate);
		}catch (DataNotFoundException e){
			throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
		}catch (DuplicateException e){
			throw new DuplicateException(ErrorCode.ERR_CATEGORY_ALREADY_EXISTS);
		}
		catch (Exception e){
			throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CATEGORY_FAIL);
		}
		return categoryDTO;
	}

	@Override
	public boolean DeleteCategory(Long id) throws DeleteDataFailException {
		boolean isDeleted = false;
		try {
			Category category = categoryRepository.findById(id)
												  .orElseThrow(()->new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND));
			category.setIsDeleted(true);
			Category deleted = categoryRepository.save(category);
			isDeleted = true;
		}catch (DataNotFoundException e){
			throw new DataNotFoundException(ErrorCode.ERR_CATEGORY_NOT_FOUND);
		}
		catch (Exception e){
			throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
		}
		return isDeleted;
	}
}
