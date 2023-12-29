package com.mes2.materials.domain;

import com.google.protobuf.Timestamp;

import lombok.Data;

@Data
public class InOutDTO {

	private int io_index; // 입출고 인덱스
	private String in_code; // 입고 코드
	private int in_quantity; // 입고 수량
	private String product_code; // 품목 코드
	private Timestamp in_regdate; // 입고 등록일
	private String pic; // 담당자
	private Timestamp out_regdate; // 출고 수정일
	private String pd_lot; // LOT번호
	private String out_code; // 출고 코드
	private String out_type; // 품목 타입
	private String based_code; // 출하 또는 작업 지시 코드
	private int out_quantity; // 출고 수량
	
}
