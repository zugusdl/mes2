package com.mes2.materials.domain;

import lombok.Data;

@Data
public class StockDTO {

	private int stock_index;
	private String pd_lot;
	private String product_code;
	private String name;
	private int quantity;
	private String status;
	private String category;
	private String out_code;
	private String in_code;
	private String warehouse_code;
}
