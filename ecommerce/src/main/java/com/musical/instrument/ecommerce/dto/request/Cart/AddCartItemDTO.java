package com.musical.instrument.ecommerce.dto.request.Cart;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class AddCartItemDTO {
    @Size(min=1,message = "Product quantity cannot be less than 1")
    private int quantity;
}
