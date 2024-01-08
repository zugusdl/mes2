package com.mes2.sales.service;

import java.util.List;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;
import com.mes2.sales.domain.SearchDTO;

public interface SalesService {

	public List<SalesDTO> salesList(Criteria cri);
	public List<SalesDTO> PlanContent(String order_code);
	public void rejectSales(List<String> odList);
	public SalesDTO stockQuantity(SalesDTO sd);
	//public List<SalesDTO> searchListPlan(SearchDTO sed); 
	public void registerPlan(PlanRegisterDTO pdto);
	//public void registerPlan(List<SalesDTO> list);
	public String checkRegPw(String user_id,String user_pw);
	public List<SalesDTO> getProdctCode(List<String>list);
	public void makeSalesCode(List<SalesDTO> sd);
	
	//public List<SalesDTO> salesAcceptList();
	public List<SalesDTO> acceptContent(String order_code);
	
	
	public void stockReg(SalesDTO sd);
	public void changeProductStatus(SalesDTO sd);
	public void productInst(SalesDTO sd);
	public void updateStockQuan(SalesDTO sd);
	
	public void insertShippingPlan(PlanRegisterDTO pdto);
	//public List<SalesDTO> getNewSales();
	public SalesDTO salesPlanCnt();
	
	//public List<SalesDTO> instructionList(String instructions);
	//public List<SalesDTO> waitList();
	//public List<SalesDTO> acceptList();
	public SalesDTO proCnt();
	//public List<SalesDTO>newAcceptList();
	//public List<SalesDTO>UserAccept(String user_id);
	
	public AcceptSaveDTO orderInfo(String order_code);
	
	public String instructSales(List<SalesDTO> list);
	
	public int totalCount(Criteria cri);
	
	public SalesDTO getRegUser(String order_code);
}
