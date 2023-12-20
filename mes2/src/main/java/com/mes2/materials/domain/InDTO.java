package com.mes2.materials.domain;

import com.google.protobuf.Timestamp;

import lombok.Data;

@Data
public class InDTO {

	private int in_index; 
	private String in_code;
	private String quantity;
	private String product_code;
	private Timestamp regdate;
	private Timestamp moddate;
	private String pic;
	
}
