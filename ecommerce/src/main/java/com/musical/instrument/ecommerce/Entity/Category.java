package com.musical.instrument.ecommerce.Entity;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "category", indexes = {
		@Index(columnList = "category_name"),
		@Index(name = "cate_parent",columnList = "category_parent" )
})
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_category", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "category_name")
	private String name;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name ="create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name ="update_date")
	private Date updateDate;

	@Column(name ="isdeleted")
	private Boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "category_parent")
	private Category parent;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "parent")
	private Set<Category> subCategories = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "category")
	private Set<Product> products = new HashSet<>();

	public Category(){
		this.setCreateDate(new Date());
		this.setIsDeleted(false);
	}
}
