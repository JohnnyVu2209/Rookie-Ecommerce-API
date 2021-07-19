package com.musical.instrument.ecommerce.dto.request.Cart;

import com.musical.instrument.ecommerce.Entity.CartItem;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDetailDTO {
    private List<CartItem> cartItems;
    private int quantity;
    private Double amount;
}
