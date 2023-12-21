package com.mes2.materials.domain;

import lombok.Data;

@Data
public class StockDTO {

	private String pd_lot; //로트번호 
	private String product_code; //품목코드 **
	private String name; //품목명 **
	private int quantity; //품목수량 
	private String category; //자재유형 **
	private String out_code; //출고코드 
	private String in_code; //입고코드
	private String warehouse_code; //창고코드
}
