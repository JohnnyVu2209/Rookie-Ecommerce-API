package com.musical.instrument.ecommerce.service;

import com.musical.instrument.ecommerce.Entity.CartItem;
import com.musical.instrument.ecommerce.Entity.Product;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDetailDTO;

public interface CartService {
    CartDetailDTO CartDetail(Long accountId);

    CartDTO AddItemToCart(Long accountId, Product product, int quantity);

    CartDTO RemoveItemFromCart(Long accountId,CartItem item);
}
