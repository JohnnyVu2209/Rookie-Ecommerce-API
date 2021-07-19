package com.musical.instrument.ecommerce.dto.request.Product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateProductDTO {
	private String name;
	private String description;
	private int quantity;
	private BigDecimal price;
	private String image;
	private Long id_category;
	private Long id_brand;
}
