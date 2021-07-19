package com.musical.instrument.ecommerce.Entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product", nullable = false)
	private Long id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "price")
	private Double price;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	private Date create_date;

	@Temporal(TemporalType.DATE)
	@Column(name = "update_date")
	private Date update_date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_category")
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_brand")
	private Brand brand;

	@OneToMany(mappedBy = "product")
	private List<CartItem> cartItems;

	@Column(name = "img")
	private String image;

	@Column(name = "isdeleted")
	private Boolean isDeleted;
	
	public Product() {
		this.create_date = new Date();
		this.isDeleted = false;
	}

}
