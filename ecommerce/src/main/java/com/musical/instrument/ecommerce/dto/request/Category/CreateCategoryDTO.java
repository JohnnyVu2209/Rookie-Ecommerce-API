package com.musical.instrument.ecommerce.dto.request.Category;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCategoryDTO {
    @NotBlank(message = "Category name cannot be blank")
    private String categoryName;

    private String categoryParentName;
}
