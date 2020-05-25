package com.order.it.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="stock")
public class Stock {

	@Column(name="stock_id")
	@Id
	private int stockId;
	
	@Column(name="total_qty")
	private float totalQty;
	
	@Column(name="unit_id")
	private int unitId;
	
	private String name;
	
	@Column(name="description")
	private String desc;
	
	private byte[] image;
	@Transient
	private MultipartFile img;
/*	
	@OneToOne(mappedBy = "stock")
	private Products products;*/
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public float getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(float totalQty) {
		this.totalQty = totalQty;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
/*	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}*/
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
	
}
