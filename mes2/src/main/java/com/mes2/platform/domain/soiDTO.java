package com.mes2.platform.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class soiDTO {
	private String soi_order_code;
	private String soi_mdb_code;
	private String soi_customer_name;
	private Timestamp soi_order_date;
	private Timestamp soi_update_date;
}
