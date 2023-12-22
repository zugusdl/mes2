package com.mes2.sales.service;

import java.util.List;

import com.mes2.sales.domain.SalesDTO;

public interface SalesService {

	public List<SalesDTO> salesPlanList();
	public List<SalesDTO> PlanContent(String order_code);
	public void rejectSales(String order_code);
	
}
