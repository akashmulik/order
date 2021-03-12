package com.order.it.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="measuring_units")
public class MeasuringUnit {

	@Id
	@Column(name="unit_id")
	private int unitId;
	
	@Column(name="name")
	private String unitName;
	
	private String desc;
	
//	@OneToOne(mappedBy = "unit")
//	private Products product;
	
	public int getId() {
		return unitId;
	}
	public void setId(int id) {
		this.unitId = id;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
/*	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}*/
	
	
}
