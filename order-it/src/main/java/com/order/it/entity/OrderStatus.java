package com.order.it.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Entity
@Table(name="order_status_codes")
@Data
public class OrderStatus {

	@Id
	private int id = 1; // temporary default value private int orderId;

	private String name;
	private String desc;

}
