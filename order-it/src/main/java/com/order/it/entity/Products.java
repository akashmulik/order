package com.order.it.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Entity
@Table(name="products")
public class Products {
	
	@Id
	@Column(name="prod_id")
	private int productId;
	
	@Column(name="prod_desc")
	private String description;
	
	@Column(name="price_per_unit")
	private double pricePerUnit;
	
	int available;
	
	@OneToOne
    @JoinColumn(name = "stock_id", insertable=false, updatable=false)
	private Stock stock;
	
	@OneToOne
    @JoinColumn(name = "unit", insertable=false, updatable=false)
	private MeasuringUnit unit;
	
	private int quantity;
	@Column(name="max_qty_limit")
	private int maxQtyLimit;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public MeasuringUnit getUnit() {
		return unit;
	}

	public void setUnit(MeasuringUnit unit) {
		this.unit = unit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMaxQtyLimit() {
		return maxQtyLimit;
	}

	public void setMaxQtyLimit(int maxQtyLimit) {
		this.maxQtyLimit = maxQtyLimit;
	}

	
}
