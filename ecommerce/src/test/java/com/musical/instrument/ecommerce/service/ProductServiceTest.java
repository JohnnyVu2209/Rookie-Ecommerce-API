package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.convert.ProductConvert;
import com.musical.instrument.ecommerce.dto.request.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.UpdateProductDTO;
import com.musical.instrument.ecommerce.exception.CreateDataFailException;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.exception.DeleteDataFailException;
import com.musical.instrument.ecommerce.exception.UpdateDataFailException;
import com.musical.instrument.ecommerce.repositpory.CategoryRepository;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productServiceMock;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private ProductConvert convert;

    @Test
    public void GetAllProduct() {
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("name").descending());
        List<Product> productsList = new ArrayList<>();
        Double price = 100000.0;
        Category category = new Category(1L, "Piano", new Date(), null, false, null, null, null);
        Brand brand = new Brand(1L, "Taylor", null, null);
        Product product1 = new Product(
                1L, "Piano", null, 10, price, new Date(), null, category, brand, null, null, false);
        Product product2 = new Product(
                2L, "Piano", null, 10, price, new Date(), null, category, brand, null, null, false);
        productsList.add(product1);
        productsList.add(product2);
        Page<Product> products = new PageImpl<>(productsList);

        when(productRepository.findAll(pageRequest)).thenReturn(products);

        Page<ProductDTO> products1 = productServiceMock.ListProduct();
        assertEquals(2, products1.getSize());
    }

    @Test
    public void GetAllProductByCategory() {
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("name").descending());
        List<Product> productsList = new ArrayList<>();
        Double price = 100000.0;
        Optional<Category> category = Optional.of(new Category(1L, "Piano", new Date(), null, false, null, null, null));
        Brand brand = new Brand(1L, "Taylor", null, null);
        Product product1 = new Product(
                1L, "Piano", null, 10, price, new Date(), null, category.get(), brand, null, null, false);
        Product product2 = new Product(
                2L, "Piano", null, 10, price, new Date(), null, category.get(), brand, null, null, false);
        productsList.add(product1);
        productsList.add(product2);
        Page<Product> products = new PageImpl<>(productsList);

        when(categoryRepository.findById(category.get().getId())).thenReturn(category);
        when(productRepository.findByCategory(category.get(), pageRequest)).thenReturn(products);

        Page<ProductDTO> productDTOS = productServiceMock.FindProductByCategory(1L);
        assertEquals(2, productDTOS.getSize());

        //Throw Exception When Category not exist
        assertThrows(DataNotFoundException.class, () -> productServiceMock.FindProductByCategory(2L));
    }

    @Test
    public void CreateProduct() throws CreateDataFailException {
        Double price = 100000.0;
        Category category = new Category(1L, "Piano", new Date(), null, false, null, null, null);
        Brand brand = new Brand(1L, "Taylor", null, null);
        Product product1 = new Product(
                1L, "Piano", null, 10, price, new Date(), null, category, brand, null, null, false);
        ProductDTO productDTO = convert.ToDto(product1);

        when(productRepository.save(product1)).thenReturn(product1);
        when(productServiceMock.CreateProduct(product1)).thenReturn(productDTO);

        ProductDTO productDTO1 = productServiceMock.CreateProduct(product1);
        assertEquals(productDTO, productDTO1);
    }

    @Test
    public void UpdateProduct() throws UpdateDataFailException {
        //Given #1
        Double price = 15000000.0;

        Category category = new Category(1L, "Piano", new Date(),
                null, false, null, null, null);

        Brand brand = new Brand(1L, "Taylor", null, null);

        Optional<Product> oldProduct = Optional.of(new Product(1L, "Piano", null,
                10, price, new Date(), null, category, brand, null, null, false));

        Product updateProduct = new Product(1L, "Grand Piano", null, 15,
                price, oldProduct.get().getCreate_date(),new Date(), category, brand, null, null, false);
        //Then #1
        assertThrows(DataNotFoundException.class, () -> productServiceMock.UpdateProduct(1L, updateProduct));

        Product product = null;

        //Given #2
        when(productRepository.findById(anyLong())).thenReturn(oldProduct);

        //Then #2
        assertThrows(UpdateDataFailException.class, () -> productServiceMock.UpdateProduct(1L, product));


        ProductDTO productDTO = new ProductDTO(
                "Grand Piano", null, 15, price, null, new Date(), new Date(), category.getId(), brand.getId_brand());

        when(productRepository.save(oldProduct.get())).thenReturn(updateProduct);
        when(convert.ToDto(updateProduct)).thenReturn(productDTO);

        //when #3
        ProductDTO productDTO1 = productServiceMock.UpdateProduct(1L, updateProduct);

        //then #3
        assertEquals("Grand Piano", productDTO1.getName());
        assertEquals(15000000.0, productDTO1.getPrice());
    }

    @Test
    public void DeleteProduct() throws DeleteDataFailException {
        assertThrows(DataNotFoundException.class, ()-> productServiceMock.DeleteProduct(1L));
        //Given
        Double price = 15000000.0;
        Category category = new Category(1L, "Piano", new Date(),
                null, false, null, null, null);
        Brand brand = new Brand(1L, "Taylor", null, null);
        Optional<Product> product1 = Optional.of(new Product(
                1L, "Piano", null, 10, price, new Date(), null, category, brand, null, null, false));

        when(productRepository.findById(anyLong())).thenReturn(product1);

        //when
        Boolean deleted = productServiceMock.DeleteProduct(1L);

        //then
        assertEquals(true,deleted);
    }
}
