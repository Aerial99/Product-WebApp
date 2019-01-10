/**
*Product class for creating products
**/
package com.cs330;
import com.google.gson.Gson;

public class Product {
	
	//Private interface
	private int id, stock;
	private double price;
	private String name,description;
	
	//Constructors
	Product() {
		id = stock = 0;
		price = 0;
		name = description = "";
	}

	Product(int id,String name,String descr,double price,int stock) {
		this.id = id;
		this.name = name;
		description = descr;
		this.price = price;
		this.stock = stock;
	}
	
	Product(String name,String descr,double price,int stock) {
		this.name = name;
		description = descr;
		this.price = price;
		this.stock = stock;
	}
	
	//public interface
	public int getStock() {
		return stock;
	}
	public double getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String toString() {
		return id + ": " + name + "-" + description + ", Price: " + price + ", Stock: " + stock;
	}
}
