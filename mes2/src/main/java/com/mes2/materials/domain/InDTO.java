package com.mes2.materials.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class InDTO { 

	private int stock_index; // 품목인덱스
	private int in_index; // 입고인덱스
	private String in_code; //입고코드
	private int in_quantity; //입고수량
	private String product_code; //품목코드 **
	private int quantity; //품목수량 **
	private String unit; // 단위 **

	private Date in_regdate; //입고등록일 
	
	private String pd_lot; //로트번호 ** 
	private int pd_quantity; //로트수량 ** 
	
	private String category; //카테고리 ** 
	private String name; //품목명 **
	
	private String RP; //원자재코드**
	private String FP; //완제품코드**
	private String WH; //창고코드**
	private String ORD; //발주코드**
}
