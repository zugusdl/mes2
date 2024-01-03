package com.mes2.sales.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.platform.service.PlatformServiceImpl;
import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;
import com.mes2.sales.domain.SearchDTO;
import com.mes2.sales.persistence.SalesDAO;

@Service
public class SalesServiceImpl implements SalesService {

	private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
	
	@Inject
	private SalesDAO sdao;
	
	@Override
	public List<SalesDTO> salesList(String sales_status) {
		logger.debug(" S : salesList() ");
		return sdao.getSalesList(sales_status);
	}
	
	@Override
	public List<SalesDTO> PlanContent(String order_code) {
		logger.debug(" S : PlanContent(String order_code) ");
		return sdao.getPlanContent(order_code);
	}
	
	@Override
	public void rejectSales(List<String> odList) {
		logger.debug(" S : rejectSales(String order_code) ");
		sdao.rejectSales(odList);
		
	}
	
	@Override
	public SalesDTO stockQuantity(SalesDTO sd) {
		logger.debug(" S : stockQuantity(SalesDTO sd) ");
		return sdao.getStockQuantity(sd);
	}
	
	@Override
	public List<SalesDTO> searchListPlan(SearchDTO sed) {
		logger.debug(" S : searchListPlan(SearchDTO sed) ");
		List<SalesDTO> list = sdao.planSearch(sed);
		for(SalesDTO sdt : list) {
			List<SalesDTO> olist = sdao.makeOrderStates(sdt.getOrder_code());
			int size = olist.size();
			int count =0;
			for(SalesDTO pdt : olist) {
				if(!pdt.getProcessing_reg().equals("N")) {
					count ++;
				}
			}
			
			if(count == size) {
				sdt.setOrderStatus("complete");
				
			}else {
				sdt.setOrderStatus("waiting");
			}
	
		}
		return list;
		
	}
	
	@Override
	public void registerPlan(PlanRegisterDTO pdto) {
		logger.debug(" S : registerPlan(PlanRegisterDTO pdto) ");	
		sdao.registerPlan(pdto);
		
		
		
	}
	
	@Override
	public String checkRegPw(String user_id,String upser_pw) {
		logger.debug(" S : checkRegPw(String user_id) ");
		String dbPw = sdao.checkRegPw(user_id);
		
		if(dbPw.equals(upser_pw)) {
			return "true";
		}else {
			return "false";
		}
		
	}
	
	@Override
	public List<SalesDTO> getProdctCode(List<String> list) {
		logger.debug(" S : getProdctCode(List<String> list) ");
		List<SalesDTO> dto = sdao.getProdctCode(list);
		
		return dto;
	}
	
	@Override
	public void makeSalesCode(List<SalesDTO> sd) {
		logger.debug(" S : makeSalesCode(List<SalesDTO> sd) ");
		
		sdao.makeSalesCode(sd);
	}
	
//	@Override
//	public List<SalesDTO> salesAcceptList() {
//		logger.debug(" S : salesAcceptList() ");
//		return sdao.getSalesAcceptList();
//	}
	
	@Override
	public List<SalesDTO> acceptContent(String order_code) {
		logger.debug(" S : acceptContent(String order_code) ");
		return sdao.getAcceptContent(order_code);
	}
	
	
	@Override
	public void changeProductStatus(SalesDTO sd) {
		logger.debug(" S : changeProductStatus(SalesDTO sd) ");
		sdao.changeProductStatus(sd);
	}
	
	@Override
	public void stockReg(SalesDTO sd) {
		logger.debug(" S :stockReg(SalesDTO sd)");
		// 출고테이블에 등록
		sdao.stockReg(sd);
		
		// 상태값 변경
		sdao.changeProductStatus(sd);
		
		// 창고에서 빼기
		//sdao.updateStockQuan(sd);
	}
	
	@Override
	public void productInst(SalesDTO sd) {
		logger.debug(" S : productInst(SalesDTO sd) ");
		sdao.productInst(sd);
		// 값 변경
		sdao.changeProductStatus(sd);
		
		
		
		
		
	}
	
	@Override
	public void updateStockQuan(SalesDTO sd) {
		logger.debug(" S :updateStockQuan(SalesDTO sd) ");
		sdao.updateStockQuan(sd);
		// 창고에서 값 빼기 
		
	}
	
	@Override
	public void insertShippingPlan(PlanRegisterDTO pdto) {
		logger.debug(" insertShippingPlan(PlanRegisterDTO pdto) ");
		List<String> orderList = pdto.getOrder_code();
		
		for(String order_code: orderList) {
			SalesDTO sdt = new SalesDTO();
			Date order_date = sdao.checkOrdeDate(order_code);
			Calendar cal = Calendar.getInstance();
			cal.setTime(order_date);
			cal.add(Calendar.DATE, -4);
			sdt.setScheduled_date(cal.getTime());
			sdt.setOrder_code(order_code);			
			sdao.insertShippingPlan(sdt);
		}
		
	}
	
	@Override
	public List<SalesDTO> getNewSales() {
		
		return sdao.getNewSales();
	}
	
	@Override
	public SalesDTO salesPlanCnt() {
		SalesDTO sdt = new SalesDTO();
		sdt.setNewCnt(sdao.getPlanNewCnt("requested"));
		sdt.setWaitingCnt(sdao.getPlanWaitCnt());
		return sdt;
	}
	
	
	@Override
	public List<SalesDTO> completeList() {
		List<SalesDTO> list = salesList("accept");
		List<SalesDTO> completeList = new ArrayList<>();
		
		for(SalesDTO sdt : list) {
			List<SalesDTO> olist = sdao.makeOrderStates(sdt.getOrder_code());
			int size = olist.size();
			int count =0;
			for(SalesDTO pdt : olist) {
				if(!pdt.getProcessing_reg().equals("N")) {
					count ++;
				}
			}
			
			if(count == size) {
				sdt.setOrderStatus("complete");
				completeList.add(sdt);
			}
	
		}
		
		return completeList;
	}
	
	@Override
	public List<SalesDTO> waitList() {
		
		List<SalesDTO> list = salesList("accept");		
		List<SalesDTO> waitList = new ArrayList<>();
		for(SalesDTO sdt : list) {
			List<SalesDTO> olist = sdao.makeOrderStates(sdt.getOrder_code());
			int size = olist.size();
			int count =0;
			for(SalesDTO pdt : olist) {
				if(!pdt.getProcessing_reg().equals("N")) {
					count ++;
				}
			}
			
			if(count != size) {
				sdt.setOrderStatus("waiting");
				waitList.add(sdt);
			}
	
		}
		
		return waitList;
	}
	
	@Override
	public List<SalesDTO> acceptList() {
		List<SalesDTO> list = salesList("accept");	
		for(SalesDTO sdt : list) {
			List<SalesDTO> olist = sdao.makeOrderStates(sdt.getOrder_code());
			int size = olist.size();
			int count =0;
			for(SalesDTO pdt : olist) {
				if(!pdt.getProcessing_reg().equals("N")) {
					count ++;
				}
			}
			
			if(count == size) {
				sdt.setOrderStatus("complete");
				
			}else {
				sdt.setOrderStatus("waiting");
			}
	
		}
		return list;
	}
	
	@Override
	public List<SalesDTO> newAcceptList() {
		
		return sdao.getNewAccept();
	}
	
	@Override
	public SalesDTO proCnt() {
		List<SalesDTO> completeList = completeList();
		List<SalesDTO> waitList = waitList();
		List<SalesDTO> newList = newAcceptList();
		
		SalesDTO dto = new SalesDTO();
		dto.setNewCnt(newList.size());
		dto.setWaitingCnt(waitList.size());
		dto.setCompleteCnt(completeList.size());
		return dto;
	}
	
	@Override
	public List<SalesDTO> UserAccept(String user_id) {
		
		List<SalesDTO> list = sdao.getUserAccept(user_id);
		for(SalesDTO sdt : list) {
			List<SalesDTO> olist = sdao.makeOrderStates(sdt.getOrder_code());
			int size = olist.size();
			int count =0;
			for(SalesDTO pdt : olist) {
				if(!pdt.getProcessing_reg().equals("N")) {
					count ++;
				}
			}
			
			if(count == size) {
				sdt.setOrderStatus("complete");
				
			}else {
				sdt.setOrderStatus("waiting");
			}
	
		}
		return list;
		
	}
	
	@Override
	public AcceptSaveDTO orderInfo(String order_code) {
		
		return sdao.getOrderInfo(order_code);
	}
	
}
