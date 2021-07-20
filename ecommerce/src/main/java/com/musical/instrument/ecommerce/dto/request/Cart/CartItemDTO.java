package com.musical.instrument.ecommerce.dto.request.Cart;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemDTO {
    private String productName;
    private int quantity;
    private BigDecimal amount;
}
