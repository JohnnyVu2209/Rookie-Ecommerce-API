package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.convert.CategoryConvert;
import com.musical.instrument.ecommerce.dto.request.Category.CategoryDTO;
import com.musical.instrument.ecommerce.dto.request.Category.CreateCategoryDTO;
import com.musical.instrument.ecommerce.dto.request.Category.UpdateCategoryDTO;
import com.musical.instrument.ecommerce.exception.*;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private CategoryConvert convert;

    @Test
    public void CategoryList(){
        Pageable pageable  = PageRequest.of(0, 2, Sort.by("name").descending());

        Category category1 = new Category();
        category1.setName("A");
        Category category2 = new Category();
        category2.setName("B");

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category2);
        Page<Category> categories = new PageImpl<>(categoryList);

        when(categoryRepository.findAll(any(Pageable.class))).thenReturn(categories);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setCategoryName("A");
        CategoryDTO categoryDTO2 = new CategoryDTO();
        categoryDTO2.setCategoryName("B");

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryDTOS.add(categoryDTO1);
        categoryDTOS.add(categoryDTO2);
        Page<CategoryDTO> categoryDTOPage = new PageImpl<>(categoryDTOS);

        Page<CategoryDTO> page = categoryService.CategoryList(1,2,"asc");
        assertEquals(2, page.getSize());
    }

    @Test
    public void AddCategory_Duplicate() throws DuplicateException {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        createCategoryDTO.setCategoryName("A1");
        createCategoryDTO.setCategoryParentName("A");

        Category category = new Category();
        category.setName("A1");
        Category parent = new Category();
        parent.setName("A");
        category.setParent(parent);

        when(convert.ToEntity(any(CreateCategoryDTO.class))).thenReturn(category);
        when(categoryRepository.existsByName(anyString())).thenReturn(true);

        assertThrows(DuplicateException.class,()-> categoryService.AddCategory(createCategoryDTO));
    }

    @Test
    public void AddCategory() throws CreateDataFailException, DuplicateException {
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO();
        createCategoryDTO.setCategoryName("A1");
        createCategoryDTO.setCategoryParentName("A");

        Category category = new Category();
        category.setName("A1");
        Category parent = new Category();
        parent.setName("A");
        category.setParent(parent);

        when(convert.ToEntity(any(CreateCategoryDTO.class))).thenReturn(category);
        when(categoryRepository.existsByName(anyString())).thenReturn(false);

        assertThrows(CreateDataFailException.class,()-> categoryService.AddCategory(any(CreateCategoryDTO.class)));

        category.setId(1L);
        when(categoryRepository.save(category)).thenReturn(category);

        CategoryDTO newCatedto = new CategoryDTO();
        newCatedto.setCategoryName("A1");

        when(convert.ToDto(category)).thenReturn(newCatedto);
        CategoryDTO categoryDTO = categoryService.AddCategory(createCategoryDTO);
        assertEquals("A1", categoryDTO.getCategoryName());
    }
    @Test
    public void UpdateCategory() throws DuplicateException, UpdateDataFailException {
        UpdateCategoryDTO updateCategoryDTO = new UpdateCategoryDTO();
        updateCategoryDTO.setCategoryName("B1");
        updateCategoryDTO.setCategoryParentName("A");

        Category oldCategory = new Category();
        oldCategory.setId(1L);
        oldCategory.setName("A1");
        Category oldParent = new Category();
        oldParent.setName("A");
        oldCategory.setParent(oldParent);

        Category updateCategory = new Category();
        updateCategory.setName("B1");
        Category parent = new Category();
        parent.setName("A");
        updateCategory.setParent(parent);

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class,() -> categoryService.UpdateCategory(1L,
                any(UpdateCategoryDTO.class)));

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(oldCategory));
        when(convert.ToEntity(any(UpdateCategoryDTO.class))).thenReturn(updateCategory);

        when(categoryRepository.existsByName(anyString())).thenReturn(true);
        assertThrows(DuplicateException.class,() -> categoryService.UpdateCategory(1L,updateCategoryDTO));

        when(categoryRepository.existsByName(anyString())).thenReturn(false);

        Category updatedCategory = new Category();
        updatedCategory.setId(1L);
        updatedCategory.setName("B1");
        Category updatedParent = new Category();
        updatedParent.setName("A");
        updatedCategory.setParent(updatedParent);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(updatedCategory.getName());

        when(categoryRepository.save(any(Category.class))).thenReturn(updateCategory);
        when(convert.ToDto(any(Category.class))).thenReturn(categoryDTO);

        CategoryDTO dto = categoryService.UpdateCategory(1L,updateCategoryDTO);

        assertEquals("B1",dto.getCategoryName());
        assertThrows(UpdateDataFailException.class,() -> categoryService.UpdateCategory(anyLong(),
                any(UpdateCategoryDTO.class)));
    }

    @Test
    public void DeleteCategory() throws DeleteDataFailException {
        Category oldCategory = new Category();
        oldCategory.setId(1L);
        oldCategory.setName("A1");
        oldCategory.setIsDeleted(false);
        Category oldParent = new Category();
        oldParent.setName("A");
        oldCategory.setParent(oldParent);

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class,()-> categoryService.DeleteCategory(1L));

        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(oldCategory));
        oldCategory.setIsDeleted(true);

        when(categoryRepository.save(null));
        assertThrows(DeleteDataFailException.class,() -> categoryService.DeleteCategory(1L));

        when(categoryRepository.save(any(Category.class))).thenReturn(oldCategory);

        boolean delete = categoryService.DeleteCategory(1L);
        assertEquals(true, delete);
    }
}
