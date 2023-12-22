package com.mes2.sales.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ShippingDTO {

	private int ship_index; //인덱스 (계획번호)
	private String ship_code; // 출하코드
	private String ship_status; //출하상태
	private Date scheduled_date; //출하 예정일
	private Date ship_date; //출하 일자
	
}
