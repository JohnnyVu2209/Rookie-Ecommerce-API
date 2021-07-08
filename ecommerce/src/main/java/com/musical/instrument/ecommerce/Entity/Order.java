package com.musical.instrument.ecommerce.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "createdate")
	private Date create_date;
	
	@Column(name = "statusid")
	private int status_id;
	
	@Column(name = "userid")
	private String username;
	
	

	public Order() {
	}



	public Order(int id, int quantity, int amount, Date create_date, int status_id, String username) {
		
		this.id = id;
		this.quantity = quantity;
		this.amount = amount;
		this.create_date = create_date;
		this.status_id = status_id;
		this.username = username;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public Date getCreate_date() {
		return create_date;
	}



	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}



	public int getStatus_id() {
		return status_id;
	}



	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
