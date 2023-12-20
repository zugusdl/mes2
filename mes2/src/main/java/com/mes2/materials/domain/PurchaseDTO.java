package com.mes2.materials.domain;

import com.google.protobuf.Timestamp;

import lombok.Data;

@Data
public class PurchaseDTO {

	private String orders_code;
	private String product_code;
	private int quantity;
	private Timestamp regdate;
	private Timestamp moddate;
	private String pic;
	private String status;
}
