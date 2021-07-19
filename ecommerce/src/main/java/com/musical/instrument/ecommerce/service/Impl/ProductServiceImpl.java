package com.musical.instrument.ecommerce.service.Impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.convert.ProductConvert;
import com.musical.instrument.ecommerce.dto.request.Product.ProductDTO;
import com.musical.instrument.ecommerce.dto.request.Product.UpdateProductDTO;
import com.musical.instrument.ecommerce.exception.*;
import com.musical.instrument.ecommerce.repositpory.*;
import com.musical.instrument.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductConvert convert;

    @Override
    public Page<ProductDTO> ListProduct() {
        // TODO Auto-generated method stub
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("name").descending());
        Page<Product> products = repository.findAll(pageRequest);
        return products.map(product -> convert.ToDto(product));
    }

    @Override
    public Page<ProductDTO> FindProductByCategory(Long cateId) {
        // TODO Auto-generated method stub
        Category category = categoryRepository.findById(cateId).orElseThrow(() -> new DataNotFoundException("CATEGORY_NOT_FOUND"));
        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("name").descending());
        Page<Product> products = repository.findByCategory(category, pageRequest);
        return products.map(product -> convert.ToDto(product));
    }

    @Override
    public ProductDTO CreateProduct(Product product) throws CreateDataFailException {
        // TODO Auto-generated method stub
        try {
            ProductDTO productDTO = convert.ToDto(repository.save(product));
            return productDTO;
        } catch (Exception e) {
            throw new CreateDataFailException("PRODUCT_CREATE_FAIL");
        }
    }

    @Override
    public ProductDTO UpdateProduct(Long productId, Product product) throws UpdateDataFailException {
        // TODO Auto-generated method stub
        Product oldProduct = repository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("PRODUCT_NOT_FOUND"));
        try {
            oldProduct.setName(product.getName());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setQuantity(product.getQuantity());
            oldProduct.setUpdate_date(new Date());
            oldProduct.setBrand(product.getBrand());
            oldProduct.setCategory(product.getCategory());
            return convert.ToDto(repository.save(oldProduct));
        } catch (Exception e) {
            // TODO: handle exception
            throw new UpdateDataFailException("PRODUCT_UPDATED_FAIL");
        }
    }

    @Override
    public Boolean DeleteProduct(Long productId) throws DeleteDataFailException {
        // TODO Auto-generated method stub
        Product product = repository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("PRODUCT_NOT_FOUND"));
        product.setIsDeleted(true);
        repository.save(product);
        return product.getIsDeleted();
    }

}
