package com.mes2.product.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ProductDTO {
	
	private String pd_lot;
	private String pd_mdmp_code;
	private String pd_quantity;
	private Date pd_period;
	private Date pd_date;
	private String pd_soi_id;
	private int pd_qc_result;
	private int pd_count;
	

	
}
