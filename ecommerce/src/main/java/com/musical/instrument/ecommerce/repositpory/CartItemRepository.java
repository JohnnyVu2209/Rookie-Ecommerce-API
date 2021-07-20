package com.musical.instrument.ecommerce.repositpory;

import com.musical.instrument.ecommerce.Entity.CartItem;
import com.musical.instrument.ecommerce.Entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CartItemRepository extends PagingAndSortingRepository<CartItem, Long> {
    Optional<CartItem> findByProduct(Product product);
}
