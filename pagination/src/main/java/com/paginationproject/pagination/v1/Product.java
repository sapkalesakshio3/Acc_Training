package com.paginationproject.pagination.v1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "products")

public class Product {

	@Id
	private String productCode;
	private String productName;
	private String productLine;
	private double buyPrice;

	public Product() {
	}

	public Product(String productCode, String productName, String productLine, double buyPrice) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productLine = productLine;
		this.buyPrice = buyPrice;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductLine() {
		return productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", productName=" + productName + ", productLine=" + productLine
				+ ", buyPrice=" + buyPrice + "]";
	}

}
