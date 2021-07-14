package com.musical.instrument.ecommerce.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_category", nullable = false)
	private int id;

	@NotBlank(message = "Vui lòng nhập tên danh mục")
	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "category",cascade = CascadeType.ALL)
	private List<Product> products;

	
	
	public Category() {
	}

	public Category(int id, String category_name) {
		super();
		this.id = id;
		this.categoryName = category_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory_name() {
		return categoryName;
	}

	public void setCategory_name(String category_name) {
		this.categoryName = category_name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
