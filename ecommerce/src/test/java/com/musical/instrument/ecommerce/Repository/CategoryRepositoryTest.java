package com.musical.instrument.ecommerce.Repository;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void TestCreateCategorySuccess(){
        Category category = new Category();
        category.setName("Bass Guitar");
        category.setParent(categoryRepository.findByName("Guitar"));
        Assert.assertNotNull(categoryRepository.save(category));
    }

    @Test
    public void TestFindCategorySuccess(){
        Assert.assertNotNull(categoryRepository.findById(9L));
    }

    @Test
    public void TestGetAllSubCategory(){
        List<Category> category = categoryRepository.findByParent(categoryRepository.findById(8L).get());
        Assert.assertEquals(1,category.size());
    }

    @Test
    public void TestFindAllWithPagingSuccess(){
        PageRequest pageRequest= PageRequest.of(0, 2, Sort.by("name").descending());
        List<Category> CategoryList = categoryRepository.findAll(pageRequest).getContent();

        Assert.assertEquals(2, CategoryList.size());
        Assert.assertEquals(CategoryList.get(0).getName(),"Guitar");
    }

    @Test
    public void TestUpdateCategory(){
        Optional<Category> category = categoryRepository.findById(9L);

        Assert.assertEquals("Bass Guitar",category.get().getName());

        category.get().setName("Aucostic Guitar");
        categoryRepository.save(category.get());

        Assert.assertEquals("Aucostic Guitar", category.get().getName());
    }
}
