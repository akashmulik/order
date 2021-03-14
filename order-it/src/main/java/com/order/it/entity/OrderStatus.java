package com.order.it.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "order_status")
public class OrderStatus {

	@Id
	private int id = 1; // temporary default value private int orderId;

	private String name;
	private String desc;

}
