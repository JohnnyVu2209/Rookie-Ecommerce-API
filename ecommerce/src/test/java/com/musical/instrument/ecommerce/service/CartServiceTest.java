package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.Entity.*;
import com.musical.instrument.ecommerce.convert.CartConvert;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDetailDTO;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import com.musical.instrument.ecommerce.repositpory.CartItemRepository;
import com.musical.instrument.ecommerce.repositpory.CartRepository;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;
import com.musical.instrument.ecommerce.security.jwt.AuthEntryPointJwt;
import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(CartServiceTest.class);
    @Autowired
    private CartService cartServiceMock;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CartItemRepository cartItemRepository;

    @MockBean
    private CartConvert cartConvert;

    @Test
    public void TestCartDetail(){
        Optional<Account> account = Optional.of(new Account("ABC", "123456", "abc@test.com"));
        account.get().setId(1L);
        Optional<Cart> cart = Optional.ofNullable(null);
        Cart testCart = new Cart(account.get());

        CartDetailDTO cartDetailDTO = new CartDetailDTO(testCart.getCartItems()
                                                                .stream()
                                                                .map(entity-> cartConvert.toCartItemDTO(entity))
                                                                .collect(Collectors.toList()),testCart.getQuantity(),
                                                                BigDecimal.valueOf(testCart.getAmount()));

        when(cartRepository.findById(anyLong())).thenReturn(cart);
        when(accountRepository.findById(anyLong())).thenReturn(account);
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);
        when(cartConvert.toDetailDto(any(Cart.class))).thenReturn(cartDetailDTO);

        CartDetailDTO detail = cartServiceMock.CartDetail(1L);
        assertEquals(0, detail.getQuantity());

    }
    @Test
    public void TestAddItemToCart(){
        Optional<Account> account = Optional.of(new Account("ABC", "123456", "abc@test.com"));
        account.get().setId(1L);
        Optional<Cart> cart = Optional.ofNullable(null);
        Cart testCart = new Cart(account.get());

        Double price = 100000.0;
        Category category = new Category(1L, "Piano", new Date(), null, false, null, null, null);
        Brand brand = new Brand(1L, "Taylor", null, null);
        Product product = new Product(
                1L, "Piano", null, 10, price, new Date(), null, category, brand, null, null, false);

        when(cartRepository.findById(anyLong())).thenReturn(cart);
        when(accountRepository.findById(anyLong())).thenReturn(account);
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        List<CartItem> cartItems = testCart.getCartItems();
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(5);
        cartItem.setAmount(price*cartItem.getQuantity());
        cartItems.add(cartItem);
        testCart.setCartItems(cartItems);
        testCart.setQuantity(cartItems.stream()
                                      .mapToInt(x -> x.getQuantity())
                                      .sum());
        testCart.setAmount(cartItems.stream()
                                    .map(CartItem::getAmount)
                                    .reduce((double) 0, (a, b) -> a + b));
        CartDTO cartDTO = new CartDTO(testCart.getQuantity(),new BigDecimal(testCart.getAmount()));

        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);
        when(cartConvert.toDto(any(Cart.class))).thenReturn(cartDTO);

        CartDTO dto = cartServiceMock.AddItemToCart(1L,product, 5);

        assertEquals(5, dto.getQuantity());
        assertEquals(new BigDecimal(500000.0),dto.getAmount());
    }

    @Test
    public void TestRemoveItemFromCart(){
        Double price = 100000.0;
        Category category = new Category(1L, "Piano", new Date(), null, false, null, null, null);
        Brand brand = new Brand(1L, "Taylor", null, null);
        Product product = new Product(
                1L, "Piano", null, 10, price, new Date(), null, category, brand, null, null, false);

        Optional<Account> account = Optional.of(new Account("ABC", "123456", "abc@test.com"));
        account.get().setId(1L);
        Cart testCart = new Cart(account.get());
        List<CartItem> cartItems = testCart.getCartItems();
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(product);
        cartItem.setQuantity(5);
        cartItem.setAmount(price*cartItem.getQuantity());
        cartItems.add(cartItem);
        testCart.setCartItems(cartItems);
        testCart.setQuantity(cartItems.stream()
                                      .mapToInt(x -> x.getQuantity())
                                      .sum());
        testCart.setAmount(cartItems.stream()
                                    .map(CartItem::getAmount)
                                    .reduce((double) 0, (a, b) -> a + b));

        Cart resultCart = testCart;
        resultCart.setQuantity(0);
        resultCart.setAmount((double) 0);
        CartDTO cartDTO = new CartDTO(0,  BigDecimal.ZERO);

        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(testCart));
        when(cartRepository.save(any(Cart.class))).thenReturn(resultCart);
        when(cartConvert.toDto(any(Cart.class))).thenReturn(cartDTO);

        CartDTO removed = cartServiceMock.RemoveItemFromCart(1L,testCart.getCartItems().get(0));

        assertEquals(0, removed.getQuantity());
        assertEquals(new BigDecimal(0), removed.getAmount());
    }
    @Test
    public void RemoveItemWithException(){
        Optional<Account> account = Optional.of(new Account("ABC", "123456", "abc@test.com"));
        assertThrows(DataNotFoundException.class,() -> cartServiceMock.RemoveItemFromCart(1L,any(CartItem.class)));
        Cart testCart = new Cart(account.get());
        when(cartRepository.findById(anyLong())).thenReturn(Optional.of(testCart));
        assertThrows(DataNotFoundException.class,() -> cartServiceMock.RemoveItemFromCart(1L,any(CartItem.class)));
    }
}
