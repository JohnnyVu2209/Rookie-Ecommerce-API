package com.musical.instrument.ecommerce.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "brand")
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_brand",nullable=false)
	private int id_brand;
	
	@NotBlank(message = "Vui lòng nhập tên thương hiệu")
	@Column(name = "brand_name")
	private String brand_name;
	
	@OneToMany(mappedBy = "brand")
	private List<Product> products;
	
	public Brand() {
	}

	public Brand(int id_brand, String brand_name) {
		this.id_brand = id_brand;
		this.brand_name = brand_name;
	}

	public int getId() {
		return id_brand;
	}

	public void setId(int id_brand) {
		this.id_brand = id_brand;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	
}
