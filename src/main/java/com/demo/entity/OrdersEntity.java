package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carts")
public class OrdersEntity {

	@Id
	@Column(name = "idcarts")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartsId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
	private CustomerEntity customerEntity;
				
	@Column(name = "subtotal")
	private double subTotal;
	
	public OrdersEntity() {
		// TODO Auto-generated constructor stub
	}

	public int getCartsId() {
		return cartsId;
	}

	public void setCartsId(int cartsId) {
		this.cartsId = cartsId;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	
	
}
