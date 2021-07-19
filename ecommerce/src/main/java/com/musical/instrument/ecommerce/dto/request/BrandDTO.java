package com.musical.instrument.ecommerce.dto.request;

import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Product;
import lombok.*;

import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
	private String brand_name;
	private Set<Product> products;
}
