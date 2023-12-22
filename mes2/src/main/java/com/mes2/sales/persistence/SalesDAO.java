package com.mes2.sales.persistence;

import java.util.List;

import com.mes2.sales.domain.SalesDTO;

public interface SalesDAO {

	public List<SalesDTO> getPlanSalesList();
	public List<SalesDTO> getPlanContent(String order_code);
	public void rejectSales(String order_code);
	
}
