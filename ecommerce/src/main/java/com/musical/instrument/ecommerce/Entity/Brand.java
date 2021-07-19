package com.musical.instrument.ecommerce.Entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "brand")
public class Brand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_brand",nullable=false)
	private Long id_brand;
	
	@NotBlank(message = "Vui lòng nhập tên thương hiệu")
	@Column(name = "brand_name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization")
	private Organization organization;

	@OneToMany(mappedBy = "brand")
	private List<Product> products;
	
}
