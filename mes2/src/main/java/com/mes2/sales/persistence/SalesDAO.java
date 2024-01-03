package com.mes2.sales.persistence;

import java.util.Date;
import java.util.List;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;
import com.mes2.sales.domain.SearchDTO;

public interface SalesDAO {

	public List<SalesDTO> getSalesList(String sales_status);
	public List<SalesDTO> getPlanContent(String order_code);
	public void rejectSales(List<String> odList);
	public SalesDTO getStockQuantity(SalesDTO sd);
	public List<SalesDTO> planSearch(SearchDTO sed);
	//public void registerPlan(List<String>list, String user_id);
	public void registerPlan(PlanRegisterDTO pdto);
	public String checkRegPw(String user_id);
	public List<SalesDTO> getProdctCode(List<String>list);
	public void makeSalesCode(List<SalesDTO> sd);
	
	//public List<SalesDTO> getSalesAcceptList();
	public List<SalesDTO> getAcceptContent(String order_code);
	public void updateAcceptStatus(AcceptSaveDTO ad);
	
	public void stockReg(SalesDTO sd);
	public void changeProductStatus(SalesDTO sd);
	public void productInst(SalesDTO sd);
	public void updateStockQuan(SalesDTO sd);
	
	public void insertShippingPlan(SalesDTO sd);
	public Date checkOrdeDate(String order_code);
	
	public List<SalesDTO> getNewSales();
	public int getPlanWaitCnt();
	public int getPlanNewCnt(String sales_status); 
	
	public List<SalesDTO>makeOrderStates(String order_code);
	public List<SalesDTO>getNewAccept();
	public List<SalesDTO>getUserAccept(String user_id);
	
}
