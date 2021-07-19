package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.musical.instrument.ecommerce.Entity.Brand;

import java.util.List;


@Repository
public interface BrandRepository extends PagingAndSortingRepository<Brand, Long> {
    public Brand findByName(String brand_name);
}
