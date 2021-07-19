package com.musical.instrument.ecommerce.Entity;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_category", nullable = false)
	private Long id;

	@NotBlank(message = "Vui lòng nhập tên danh mục")
	@Column(name = "category_name")
	private String name;

	@Column(name ="create_date")
	private Date creatDate;

	@Column(name ="update_date")
	private Date updateDate;

	@Column(name ="isdeleted")
	private Boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "category_parent")
	private Category parent;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
	private List<Category> subCategories = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
	private List<Product> products;

	public Category(){
		this.setCreatDate(new Date());
		this.setIsDeleted(false);
	}
}
