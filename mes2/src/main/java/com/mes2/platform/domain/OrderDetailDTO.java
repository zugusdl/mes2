package com.mes2.platform.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class OrderDetailDTO {
	private String product_code;
	private String name;
	private double price;
	private int sales_quantity;
	
	@Override
	public String toString() {
		return "orderDetailDTO [product_code=" + product_code + ", name=" + name + ", price=" + price
				+ ", sales_quantity=" + sales_quantity + "]";
	}
}
