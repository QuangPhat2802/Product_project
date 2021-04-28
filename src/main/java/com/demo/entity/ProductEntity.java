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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Product")
public class ProductEntity {
	@Id
	@Column(name = "product_Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(name = "product_Name")
	@NotEmpty(message = "product name field can not null")
	@Size(max = 5, message = "product name should be less than 5")
	private String productName;

	@Column(name = "description")
	@NotEmpty(message = "description field can not null")
	private String description;

	@Column(name = "quantity")
	@NotNull(message = "quantity field can not null")
	@DecimalMin(value = "1", message = "quantity must be a number whose value must be hight or equal to 1")
	private int quantity;

	@Column(name = "price")
	@NotNull(message = "price field can not null")
	@DecimalMin(value = "1000", message = "quantity must be a number whose value must be hight or equal to 1000")
	private double price;

	@JsonIgnore
	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY)
	private Set<OrdersDetailsEntity> ordersDetailsEntity;

	public ProductEntity() {
		// TODO Auto-generated constructor stub
	}

	public ProductEntity(String productName, int quantity, String description, double price) {

		this.productName = productName;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<OrdersDetailsEntity> getOrdersDetailsEntity() {
		return ordersDetailsEntity;
	}

	public void setOrdersDetailsEntity(Set<OrdersDetailsEntity> ordersDetailsEntity) {
		this.ordersDetailsEntity = ordersDetailsEntity;
	}
}
