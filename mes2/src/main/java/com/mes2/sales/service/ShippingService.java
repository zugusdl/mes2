package com.mes2.sales.service;

import java.util.Date;
import java.util.List;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.SearchDTO;
import com.mes2.sales.domain.ShippingDTO;

public interface ShippingService {

	public List<ShippingDTO> shippingList();
	public List<String> productStatusCnt(String order_code);
	public void updateShipStatus(ShippingDTO sdto);
	
	public List<ShippingDTO> planContent(String order_code);
	public List<ShippingDTO> planSearch(SearchDTO sed);
	public ShippingDTO getId(String order_code);
	public ShippingDTO checkUpdatePw(String user_id,String user_pw, String order_code);
	public int getScheduleDate(Date schedule_date);
	
	public void updateSchedule(Date scheduled_date, String order_code );
	public AcceptSaveDTO getOrderInfo(String order_code);
	
	public ShippingDTO countShipStatus();
	public List<ShippingDTO> statusList(String ship_status);
	
	public String shipRegister(String order_code);
	public List<ShippingDTO> UserShipPlanList(String user_id);
	public List<ShippingDTO> instructionList();
	
	public ShippingDTO countShipProgressing();
	public List<ShippingDTO> progressList(String progress_status);
	
	public List<ShippingDTO> shipContent(String order_code);
	public void updateShipDate(Date scheduled_date, String order_code );
	
	public List<ShippingDTO> userInstructionList(String user_id);
	
	public List<ShippingDTO> shippingSearch(SearchDTO sed);
}
