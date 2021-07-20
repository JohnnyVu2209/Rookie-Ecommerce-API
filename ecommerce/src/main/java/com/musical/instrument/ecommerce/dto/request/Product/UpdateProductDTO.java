package com.musical.instrument.ecommerce.dto.request.Product;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateProductDTO {
	@NotBlank(message = "Product cannot be blank")
	private String name;

	private String description;

	@Min(value = 0, message = "Product quantity cannot be less than 0")
	private int quantity;

	@Min(value = 1000, message = "Product price cannot be less than 1000d")
	private BigDecimal price;

	private String link_img;

	private Long id_category;

	private Long id_brand;
}
