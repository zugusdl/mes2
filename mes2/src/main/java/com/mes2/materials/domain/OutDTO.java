package com.mes2.materials.domain;

import com.google.protobuf.Timestamp;

import lombok.Data;
@Data
public class OutDTO {
	
	private int out_index; // 출고인덱스
	private String out_code; //출고코드
	private int out_quantity; //출고수량 
	private String product_code; //품목코드 **
	private Timestamp out_regdate; //출고등록일 
	private String pd_lot; //로트번호 ** 
	private int pd_quantity; //로트수량 ** 
	private String out_type; //품목 타입 * P: 생산, S: 출하
	private String based_code; //출하 또는 작업 지시 코드 **

	private String category; //카테고리 ** 
	private String name; //품목명 **
	
	private String RP; //원자재코드**
	private String FP; //완제품코드**
	private String WH; //창고코드**
	private String ORD; //발주코드**
}
