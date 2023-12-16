package com.mes2.platform.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class soiDTO {
	private String soi_order_code;
	private String soi_mdb_code;
	private String soi_customer_name;
	private Date soi_order_date;
	private Date soi_update_date;
}
