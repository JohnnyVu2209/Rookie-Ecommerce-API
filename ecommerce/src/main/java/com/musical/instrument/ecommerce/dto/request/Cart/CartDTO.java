package com.musical.instrument.ecommerce.dto.request.Cart;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {
    private int Quantity;
    private Double amount;
}
