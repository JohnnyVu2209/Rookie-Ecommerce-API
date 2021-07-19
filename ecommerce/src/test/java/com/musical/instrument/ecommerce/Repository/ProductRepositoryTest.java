package com.musical.instrument.ecommerce.Repository;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.repositpory.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void TestCreateProductSuccess() {
        Product product = new Product();
        product.setName("Fender CC-60S");
        product.setQuantity(10);
        product.setPrice(10000000.0);
        Brand brand = brandRepository.findByName("Fender");
        product.setBrand(brand);
        Category category = categoryRepository.findByName("Aucostic Guitar");
        product.setCategory(category);
        assertNotNull(productRepository.save(product));
    }
    @Test
    public void TestFindAllWithPagingSuccess(){
        PageRequest pageRequest= PageRequest.of(0, 2, Sort.by("name").descending());
        List<Product> products = productRepository.findAll(pageRequest).getContent();

        Assert.assertEquals(2, products.size());
    }
}
