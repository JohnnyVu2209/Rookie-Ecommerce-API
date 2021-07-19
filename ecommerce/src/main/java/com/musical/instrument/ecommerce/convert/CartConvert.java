package com.musical.instrument.ecommerce.convert;

import com.musical.instrument.ecommerce.Entity.Cart;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDTO;
import com.musical.instrument.ecommerce.dto.request.Cart.CartDetailDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartConvert {
    @Autowired
    private ModelMapper mapper;

    public CartDetailDTO toDetailDto(Cart cart){
        CartDetailDTO cartDetailDTO = mapper.map(cart, CartDetailDTO.class);
        return  cartDetailDTO;
    }
    public CartDTO toDto(Cart cart){
        CartDTO cartDTO = mapper.map(cart, CartDTO.class);
        return  cartDTO;
    }
}
