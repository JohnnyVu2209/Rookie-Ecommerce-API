package com.musical.instrument.ecommerce.dto.request.Category;

import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.dto.request.Category.CategoryChildDTO;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO {

	@NotBlank(message = "Category name cannot be blank")
	private String categoryName;

	private Set<CategoryChildDTO> categoriesChildName;
}
