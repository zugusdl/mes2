package com.mes2.platform.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class SoiDTO {
	private int sales_index;
	private String order_code;
	private String company_code;
	private Date request_date;
	private Date order_date;
	private Date update_date;
	private Date acceptance_date;
	private int user_id;
	private String sales_status;
}
