package com.musical.instrument.ecommerce.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.musical.instrument.ecommerce.composite.ProductRatingID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "product_rate")
@Getter
@Setter
@NoArgsConstructor
@IdClass(ProductRatingID.class)
public class Product_rate {

	@Id
	@Column (name = "username")
	private String username;
	
	@Id
	@Column (name = "product_id")
	private int product_id;
	
	@Column (name = "rating")
	private int rating;

	
}
