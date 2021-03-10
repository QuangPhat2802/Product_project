package com.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Customer")
public class CustomerEntity {
	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "phone")
	private int customerPhone;

	@JsonIgnore
	@OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
	private Set<OrdersDetailsEntity> ordersDetailsEntity;

	@JsonIgnore
	@OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
	private Set<OrdersEntity> ordersEntity;

	public CustomerEntity() {

	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(int customerPhone) {
		this.customerPhone = customerPhone;
	}

	public Set<OrdersDetailsEntity> getOrdersDetailsEntity() {
		return ordersDetailsEntity;
	}

	public void setOrdersDetailsEntity(Set<OrdersDetailsEntity> ordersDetailsEntity) {
		this.ordersDetailsEntity = ordersDetailsEntity;
	}

	public Set<OrdersEntity> getOrdersEntity() {
		return ordersEntity;
	}

	public void setOrdersEntity(Set<OrdersEntity> ordersEntity) {
		this.ordersEntity = ordersEntity;
	}

}
