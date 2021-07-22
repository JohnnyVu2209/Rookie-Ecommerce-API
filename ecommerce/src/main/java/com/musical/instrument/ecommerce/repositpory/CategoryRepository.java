package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.musical.instrument.ecommerce.Entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    Optional<Category> findByName(String category_name);
    Boolean existsByName(String name);
    @Query("Select c from Category c where c.parent = null and c.isDeleted = false")
    Page<Category> findAllParent(Pageable pageable);


}
