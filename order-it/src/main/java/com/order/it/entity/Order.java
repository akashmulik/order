package com.order.it.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Entity
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private String mobileNo;
	private int orderStatusId;
	private String orderDate;
	private String lastUpdatedDate;
	private String lastUpdatedBy;
	private String statusUpdatedDate;
	
	@OneToOne
	@JoinColumn(name = "order_status_id", insertable=false, updatable=false)
	private OrderStatus orderStatus;
	
	@Column(insertable = false, updatable = false)
	@OneToMany(mappedBy = "orderId")
	private Set<LiveOrder> liveOrder;
}
