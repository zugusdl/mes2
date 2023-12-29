package com.mes2.sales.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ShippingDTO {

	private String order_code; //주문코드 (sales_order_info)
	private String company_name; // 거래처 회사명 - 수주처 (meta_data_business 테이블 / name)
	private Date order_date; //납품요청일 (sales_order_info)
	private int sales_quantity; //제품수량 - 수주량 (sales_order_product)
	private String ship_status; //출하상태
	
	
	
	private int ship_index; //인덱스 (계획번호)
	private String ship_code; // 출하코드
	private Date scheduled_date; //출하 예정일
	private Date ship_date; //출하 일자
	
}
