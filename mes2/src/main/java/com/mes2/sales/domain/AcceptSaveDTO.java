package com.mes2.sales.domain;

import java.util.List;

import lombok.Data;

@Data
public class AcceptSaveDTO {

	 private String order_code;
	 private List<String> product_code;
	 private List<String> processing_reg;
	 private List<String> sales_code;
	 private List<String> sales_quantity;
}
