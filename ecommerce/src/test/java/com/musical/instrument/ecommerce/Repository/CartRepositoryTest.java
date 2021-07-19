package com.musical.instrument.ecommerce.Repository;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.Cart;
import com.musical.instrument.ecommerce.Entity.CartItem;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import com.musical.instrument.ecommerce.repositpory.CartItemRepository;
import com.musical.instrument.ecommerce.repositpory.CartRepository;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Test
    public void CreateCart(){
        Account account = new Account("ABC",
                                "123456",
                                    "abc@email.com");
        accountRepository.save(account);

        Cart cart = new Cart(accountRepository.findByUsername("ABC").get());
        assertNotNull(cartRepository.save(cart));
    }

    @Test
    public void AddItemAndUpdateCart(){
        Optional<Account> account = accountRepository.findByUsername("ABC");
        CartItem cartItem = new CartItem();
        cartItem.setProduct(productRepository.getById(5L));
        cartItem.setAmount(productRepository.getById(5L).getPrice());
        cartItem.setQuantity(5);

        assertNotNull(cartItemRepository.save(cartItem));
/*
        Cart cart = cartRepository.getById(account.getId());
        assertEquals(5,cart.getQuantity());*/
    }
}
