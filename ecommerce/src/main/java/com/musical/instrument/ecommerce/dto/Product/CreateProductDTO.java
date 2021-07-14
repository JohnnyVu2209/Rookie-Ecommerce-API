package com.musical.instrument.ecommerce.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO {
	private String product_name;
	private String description;
	private int quantity;
	private int price;
	private String link_img;
	private int id_category;
	private int id_brand;

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLink_img() {
		return link_img;
	}

	public void setLink_img(String link_img) {
		this.link_img = link_img;
	}

	public int getId_category() {
		return id_category;
	}

	public void setId_category(int id_category) {
		this.id_category = id_category;
	}

	public int getId_brand() {
		return id_brand;
	}

	public void setId_brand(int id_brand) {
		this.id_brand = id_brand;
	}

}
