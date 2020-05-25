package com.order.it.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.order.it.entity.comppk.CartPK;

@Entity
@Table(name= "live_orders")
public class LiveOrder {

	@EmbeddedId
	private CartPK id;
	private int qty;
	private double amount;
	private String status;
	@Column(name = "order_placed_on", columnDefinition = "TIMESTAMP")
	private String orderPlacedOn;
	@OneToOne
    @JoinColumn(name = "prod_id", insertable=false, updatable=false)
	private Products product;
	@Column(name="price_per_unit")
	private double pricePerUnit;
	
	public CartPK getId() {
		return id;
	}
	public void setId(CartPK id) {
		this.id = id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrderPlacedOn() {
		return orderPlacedOn;
	}
	public void setOrderPlacedOn(String orderPlacedOn) {
		this.orderPlacedOn = orderPlacedOn;
	}
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	
}
