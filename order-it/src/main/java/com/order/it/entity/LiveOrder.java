package com.order.it.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.order.it.entity.comppk.CartPK;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name= "live_order")
@Data
public class LiveOrder {

//	@EmbeddedId
//	private CartPK id;
	private int prodId;
	private int qty;
	private double amount;
//	@Column(name = "order_placed_on", columnDefinition = "TIMESTAMP")
//	private String orderPlacedOn;
	
	@OneToOne
    @JoinColumn(name = "prod_id", insertable=false, updatable=false)
	private Products product;
	@Column(name="price_per_unit")
	private double pricePerUnit;
	
	@Column(name="order_id")
	private int orderId;

}
