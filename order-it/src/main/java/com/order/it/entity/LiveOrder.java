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
	@Column(name = "order_placed_on", columnDefinition = "TIMESTAMP")
	private String orderPlacedOn;
	@OneToOne
    @JoinColumn(name = "prod_id", insertable=false, updatable=false)
	private Products product;
	@Column(name="price_per_unit")
	private double pricePerUnit;
	@Column(name="order_id")
	private int orderId;
	@OneToOne
	@JoinColumn(name = "status_id", insertable=false, updatable=false)
	private OrderStatus oStatus;
	
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
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "LiveOrder [orderId=" + orderId + "]";
	}
	public OrderStatus getoStatus() {
		return oStatus;
	}
	
}
