package com.musical.instrument.ecommerce.convert;

import com.musical.instrument.ecommerce.Entity.Cart;
import com.musical.instrument.ecommerce.Entity.CartItem;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDetailDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartItemDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Component
public class CartConvert {
    @Autowired
    private ModelMapper mapper;

    public CartDetailDTO toDetailDto(Cart cart){
        CartDetailDTO cartDetailDTO = mapper.map(cart, CartDetailDTO.class);
        cartDetailDTO.setCartItems(cart.getCartItems()
                                        .stream().map(entity-> toCartItemDTO(entity))
                                        .collect(Collectors.toList()));
        cartDetailDTO.setAmount(new BigDecimal(cart.getAmount()));
        return  cartDetailDTO;
    }
    public CartDTO toDto(Cart cart){
        CartDTO cartDTO = mapper.map(cart, CartDTO.class);
        cartDTO.setAmount(new BigDecimal(cart.getAmount()));
        return  cartDTO;
    }
    public CartItemDTO toCartItemDTO(CartItem item){
        CartItemDTO cartItemDTO = mapper.map(item,CartItemDTO.class);
        cartItemDTO.setProductName(item.getProduct().getName());
        cartItemDTO.setAmount(new BigDecimal(item.getAmount()));
        return cartItemDTO;
    }
}
