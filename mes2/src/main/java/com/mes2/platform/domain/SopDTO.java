package com.mes2.platform.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class SopDTO {
	private int sales_product_index;
	private String sales_code;
	private String product_code;
	private int sales_quantity;
	private String product_status;
	private String processing_reg;
	private String order_code;

	@Override
	public String toString() {
		return "SopDTO [sales_product_index=" + sales_product_index + ", sales_code=" + sales_code + ", product_code="
				+ product_code + ", sales_quantity=" + sales_quantity + ", product_status=" + product_status
				+ ", processing_reg=" + processing_reg + ", order_code=" + order_code + "]";
	}
}
