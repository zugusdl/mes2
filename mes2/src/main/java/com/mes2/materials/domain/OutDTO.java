package com.mes2.materials.domain;

import com.google.protobuf.Timestamp;

import lombok.Data;

@Data
public class OutDTO {

	private int out_index; 
	private String out_code;
	private String type;
	private String based_code;
	private String product_code;
	private int quantity;
	private Timestamp regdate;
	private Timestamp moddate;
	private String pic;
}
