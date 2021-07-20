package com.musical.instrument.ecommerce.dto.request;

import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.Entity.Category;
import com.musical.instrument.ecommerce.dto.request.Product.ProductDTO;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

	@NotBlank(message = "Category name cannot be blank")
	private String Category_name;

	private Set<ProductDTO> products = new HashSet<>();
}
