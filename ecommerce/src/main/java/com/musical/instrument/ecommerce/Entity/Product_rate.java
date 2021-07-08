package com.musical.instrument.ecommerce.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.musical.instrument.ecommerce.composite.ProductRatingID;

@Entity
@Table (name = "product_rate")
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

	
	public Product_rate() {
	}


	public Product_rate(String username, int product_id, int rating) {
		this.username = username;
		this.product_id = product_id;
		this.rating = rating;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
}
