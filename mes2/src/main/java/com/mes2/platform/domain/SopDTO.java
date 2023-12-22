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
}
