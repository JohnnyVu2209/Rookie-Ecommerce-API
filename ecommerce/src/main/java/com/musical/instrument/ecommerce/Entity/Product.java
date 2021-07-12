package com.musical.instrument.ecommerce.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product", schema = "public")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product", nullable = false)
	private int id;

	@Column(name = "productname")
	private String product_name;

	@Column(name = "description")
	private String description;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private int price;

	@Temporal(TemporalType.DATE)
	@Column(name = "createdate")
	private Date create_date;

	@Temporal(TemporalType.DATE)
	@Column(name = "updatedate")
	private Date update_date;

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "id_category", nullable = false)
	private Category category;

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "id_brand", nullable = false)
	private Brand brand;

	@Column(name = "img")
	private String link_img;

	public Product() {
	}

	public Product(int id, String product_name, String description, int quantity, int price, Date create_date,
			Date update_date, Category category, Brand brand, String link_img) {
		this.id = id;
		this.product_name = product_name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.create_date = create_date;
		this.update_date = update_date;
		this.category = category;
		this.brand = brand;
		this.link_img = link_img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getLink_img() {
		return link_img;
	}

	public void setLink_img(String link_img) {
		this.link_img = link_img;
	}

}
