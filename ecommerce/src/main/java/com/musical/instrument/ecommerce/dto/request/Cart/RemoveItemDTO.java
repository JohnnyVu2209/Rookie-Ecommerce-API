package com.musical.instrument.ecommerce.dto.request.Cart;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Data
public class RemoveItemDTO {
    @NotNull
    private Long id;

    @NotNull
    private int quantity;
}
