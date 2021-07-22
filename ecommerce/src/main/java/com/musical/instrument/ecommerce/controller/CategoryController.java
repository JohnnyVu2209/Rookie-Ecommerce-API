package com.musical.instrument.ecommerce.controller;

import com.musical.instrument.ecommerce.dto.request.Category.CategoryDTO;
import com.musical.instrument.ecommerce.dto.request.Category.CreateCategoryDTO;
import com.musical.instrument.ecommerce.dto.request.Category.UpdateCategoryDTO;
import com.musical.instrument.ecommerce.dto.response.ErrorCode;
import com.musical.instrument.ecommerce.dto.response.ResponseDTO;
import com.musical.instrument.ecommerce.dto.response.SuccessCode;
import com.musical.instrument.ecommerce.exception.*;
import com.musical.instrument.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{PageNo}")
    public ResponseEntity<ResponseDTO> getAllCategory(@PathVariable("PageNo") int pageNo,
                                                @RequestParam("sortDir") String sortDir) throws LoadDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            int pageSize = 5;
            Page<CategoryDTO> categoryDTO = categoryService.CategoryList(pageNo,pageSize, sortDir);
            responseDTO.setData(categoryDTO.getContent());
            responseDTO.setSuccessCode(SuccessCode.CATEGORY_LIST_LOAD_SUCCESS);
        }catch (Exception e){
            throw new LoadDataFailException(ErrorCode.ERR_CATEGORY_LIST_LOAD_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> CreateCategory(@Valid @RequestBody CreateCategoryDTO createCategoryDTO)
            throws CreateDataFailException, DuplicateException {
        ResponseDTO responseDTO = new ResponseDTO();
        CategoryDTO categoryDTO = categoryService.AddCategory(createCategoryDTO);
        responseDTO.setData(categoryDTO);
        responseDTO.setSuccessCode(SuccessCode.CREATE_CATEGORY_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> UpdateCategory(@PathVariable("id") Long id,
            @Valid @RequestBody UpdateCategoryDTO updateCategoryDTO)
            throws DuplicateException, UpdateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        CategoryDTO categoryDTO = categoryService.UpdateCategory(id,updateCategoryDTO);
        responseDTO.setData(categoryDTO);
        responseDTO.setSuccessCode(SuccessCode.UPDATE_CATEGORY_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> DeleteCategory(@PathVariable("id") Long id) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        boolean isDeleted = categoryService.DeleteCategory(id);
        if(isDeleted)
            responseDTO.setSuccessCode(SuccessCode.DELETE_CATEGORY_SUCCESS);
        else
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_CATEGORY_FAIL);
        return ResponseEntity.ok().body(responseDTO);
    }
}
