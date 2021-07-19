package com.musical.instrument.ecommerce.repositpory;

import com.musical.instrument.ecommerce.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
