package com.musical.instrument.ecommerce.composite;

import java.io.Serializable;

public class ProductRatingID implements Serializable{
	private String username;
	
	private int product_id;

	public ProductRatingID(String username, int product_id) {
		this.username = username;
		this.product_id = product_id;
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
	
	
}
