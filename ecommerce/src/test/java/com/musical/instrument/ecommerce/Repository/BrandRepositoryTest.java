package com.musical.instrument.ecommerce.Repository;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.repositpory.BrandRepository;
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
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void TestCreateBrandSuccess(){
        Brand newBrand = new Brand();
        newBrand.setName("Taylor");
        Assert.assertNotNull(brandRepository.save(newBrand));
    }

    @Test
    public void TestFindBrandSuccess(){
        Assert.assertNotNull(brandRepository.findById(4L));
    }

    @Test
    public void TestFindBrandNameSuccess(){
        Assert.assertNotNull(brandRepository.findByName("Jackson"));
    }

    @Test
    public void TestFindAllWithPagingSuccess(){
        PageRequest pageRequest= PageRequest.of(0, 2, Sort.by("name").descending());
        List<Brand> brandList = brandRepository.findAll(pageRequest).getContent();

        Assert.assertEquals(2, brandList.size());
        Assert.assertEquals(brandList.get(0).getName(),"Taylor");
    }

    @Test
    public void TestUpdateBrand(){
        Optional<Brand> brand = brandRepository.findById(6L);

        Assert.assertEquals("Taylor",brand.get().getName());

        brand.get().setName("Roland");
        brandRepository.save(brand.get());

        Assert.assertEquals("Roland", brand.get().getName());
    }

    @Test
    public void TestDeleteBrand(){
        Optional<Brand> brand = brandRepository.findById(5L);

        Assert.assertNotNull(brand.get());

        brandRepository.delete(brand.get());

        Assert.assertNull(brand.get().getId_brand());
    }

}
