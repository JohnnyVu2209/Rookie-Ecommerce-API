package com.musical.instrument.ecommerce.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.musical.instrument.ecommerce.Entity.Brand;
import com.musical.instrument.ecommerce.Entity.Category;
import com.sun.istack.Nullable;

public class ProductDTO {

	private int id;

	@NotBlank(message = "Vui lòng nhập tên sản phẩm")
	private String product_name;

	private String description;

	@Min(value = 0, message = "số lượng hàng không được nhỏ hơn 0")
	private int quantity;

	@Min(value = 1000, message = "giá trị của sản phẩm không được nhỏ hơn 1000đ")
	private int price;

	private CategoryDTO categoryDTO;

	private BrandDTO brandDTO;

	private String link_img;

	@Nullable
	private Date create_date;

	@Nullable
	private Date update_date;

	public ProductDTO() {
	}

	public ProductDTO(int id, @NotBlank(message = "Vui lòng nhập tên sản phẩm") String product_name, String description,
			@Min(value = 0, message = "số lượng hàng không được nhỏ hơn 0") int quantity,
			@Min(value = 1000, message = "giá trị của sản phẩm không được nhỏ hơn 1000đ") int price, String link_img) {
		this.id = id;
		this.product_name = product_name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
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

	public String getLink_img() {
		return link_img;
	}

	public void setLink_img(String link_img) {
		this.link_img = link_img;
	}

	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public BrandDTO getBrandDTO() {
		return brandDTO;
	}

	public void setBrandDTO(BrandDTO brandDTO) {
		this.brandDTO = brandDTO;
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

}
