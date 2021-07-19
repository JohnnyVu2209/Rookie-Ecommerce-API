package com.musical.instrument.ecommerce.dto.request.Product;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;
import com.sun.istack.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

	private String name;
	private String description;
	private int quantity;
	private Double price;
	private String link_img;
	private Date create_date;
	private Date update_date;
	private Long id_category;
	private Long id_brand;
}
