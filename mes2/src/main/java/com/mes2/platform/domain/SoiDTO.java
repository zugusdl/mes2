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
	private String user_id;
	private String sales_status;
	
	@Override
	public String toString() {
		return "SoiDTO [sales_index=" + sales_index + ", order_code=" + order_code + ", company_code=" + company_code
				+ ", request_date=" + request_date + ", order_date=" + order_date + ", update_date=" + update_date
				+ ", acceptance_date=" + acceptance_date + ", user_id=" + user_id + ", sales_status=" + sales_status
				+ "]";
	}
}
