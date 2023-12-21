package com.mes2.materials.domain;

import com.google.protobuf.Timestamp;

import lombok.Data;

@Data
public class PurchaseDTO {

	private String orders_code; //발주코드
	private String product_code; //발주 품목코드 원자재 **
	private int quantity; // 발주수량
	private Timestamp regdate; // 발주등록일
	private Timestamp moddate; // 발주수정일=발주변경일
	private String user_id; // 담당자
	private String status; // 진행상황
}
