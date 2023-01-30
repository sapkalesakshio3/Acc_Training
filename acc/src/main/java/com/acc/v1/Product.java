package com.acc.v1;

import jakarta.persistence.Entity;   
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "product")
public class Product {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String name;
    private float price;

    public Product() {
    }
    public Integer getId() {
        return id;
    }
	public Product(Integer id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    
	
    
}
