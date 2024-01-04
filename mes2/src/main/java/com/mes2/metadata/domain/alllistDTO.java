package com.mes2.metadata.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class alllistDTO {
	private String startDate;
	private String endDate;
	private String search;
	private Criteria Cri;
}
