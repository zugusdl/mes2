package com.mes2.sales.service;

import java.util.List;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;
import com.mes2.sales.domain.SearchDTO;

public interface SalesService {

	public List<SalesDTO> salesPlanList();
	public List<SalesDTO> PlanContent(String order_code);
	public void rejectSales(List<String> odList);
	public SalesDTO stockQuantity(SalesDTO sd);
	public List<SalesDTO> searchListPlan(SearchDTO sed); 
	public void registerPlan(PlanRegisterDTO pdto);
	//public void registerPlan(List<SalesDTO> list);
	public String checkRegPw(String user_id,String user_pw);
	public List<SalesDTO> getProdctCode(List<String>list);
	public void makeSalesCode(List<SalesDTO> sd);
	
	public List<SalesDTO> salesAcceptList();
	public List<SalesDTO> acceptContent(String order_code);
	public void changeAcceptStatus(AcceptSaveDTO ad);
	
	public void stockReg(SalesDTO sd);
	public void changeProductStatus(SalesDTO sd);
	public void productInst(SalesDTO sd);
	public void updateStockQuan(SalesDTO sd);
}
