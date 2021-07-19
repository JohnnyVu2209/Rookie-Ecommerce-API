package com.musical.instrument.ecommerce.repositpory;

import com.musical.instrument.ecommerce.Entity.CartItem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CartItemRepository extends PagingAndSortingRepository<CartItem, Long> {
}
