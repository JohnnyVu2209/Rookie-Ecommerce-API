package com.musical.instrument.ecommerce.service.Impl;

import com.musical.instrument.ecommerce.Entity.Account;
import com.musical.instrument.ecommerce.Entity.Cart;
import com.musical.instrument.ecommerce.Entity.CartItem;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.convert.CartConvert;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDetailDTO;
import com.musical.instrument.ecommerce.dto.response.ErrorCode;
import com.musical.instrument.ecommerce.exception.DataNotFoundException;
import com.musical.instrument.ecommerce.repositpory.AccountRepository;
import com.musical.instrument.ecommerce.repositpory.CartItemRepository;
import com.musical.instrument.ecommerce.repositpory.CartRepository;
import com.musical.instrument.ecommerce.repositpory.ProductRepository;
import com.musical.instrument.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartConvert cartConvert;

    @Override
    public CartDetailDTO CartDetail(Long accountId) {
        Optional<Cart> cart = cartRepository.findById(accountId);
        if(cart.isEmpty()){
            Optional<Account> account = accountRepository.findById(accountId);
            Cart newCart = new Cart(account.get());
            cart = Optional.of(cartRepository.save(newCart));
        }
        return cartConvert.toDetailDto(cart.get());
    }

    @Override
    public CartDTO AddItemToCart(Long accountId ,Product product, int quantity) {
        Optional<Cart> cart = cartRepository.findById(accountId);
        if(cart.isEmpty()){
            Optional<Account> account = accountRepository.findById(accountId);
            Cart newCart = new Cart(account.get());
            cart = Optional.of(cartRepository.save(newCart));
        }
        List<CartItem> cartItems = cart.get().getCartItems();
        Optional<CartItem> cartItem = cartItemRepository.findByProduct(product);
        if(cartItem.isEmpty()){
            cartItem  = Optional.of(new CartItem(product, quantity, cart.get()));
            cartItems.add(cartItem.get());
        }else {
            int index = cartItems.indexOf(cartItem.get());
            cartItem.get().setQuantity(cartItem.get().getQuantity()+ quantity);
            cartItems.set(index,cartItem.get());
        }
        CartDTO cartDTO = cartConvert.toDto(caculate(cart, cartItems));
        cartItemRepository.save(cartItem.get());
        return cartDTO;
    }

    @Override
    public CartDTO RemoveItemFromCart(Long accountId,CartItem item) {
        Cart cart = cartRepository.findById(accountId)
                                  .orElseThrow(() -> new DataNotFoundException(ErrorCode.ERR_CART_NOT_HAVE_ITEM));
        List<CartItem> cartItems = cart.getCartItems();
        if(cartItems.contains(item)){
            cartItems.remove(item);
            cartItemRepository.delete(item);
        }
        else
            throw new DataNotFoundException(ErrorCode.ERR_ITEM_NOT_EXIST_IN_CART);
        Cart cart1 = caculate(Optional.of(cart), cartItems);
        return cartConvert.toDto(cart1);
    }

    private Cart caculate(Optional<Cart> cart, List<CartItem> cartItems){
        cart.get().setCartItems(cartItems);
        cart.get().setQuantity(cartItems.stream()
                                        .mapToInt(x -> x.getQuantity())
                                        .sum());
        cart.get().setAmount(cartItems.stream()
                                      .map(CartItem::getAmount)
                                      .reduce((double) 0, (a, b) -> a + b));
        return cartRepository.save(cart.get());
    }
}
