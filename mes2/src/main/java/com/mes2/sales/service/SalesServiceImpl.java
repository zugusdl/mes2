package com.mes2.sales.service;

import java.util.ArrayList;
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
	public List<SalesDTO> salesPlanList() {
		logger.debug(" S : salesList() ");
		return sdao.getSalesPlanList();
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
		return sdao.planSearch(sed);
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
	
	@Override
	public List<SalesDTO> salesAcceptList() {
		logger.debug(" S : salesAcceptList() ");
		return sdao.getSalesAcceptList();
	}
	
	@Override
	public List<SalesDTO> acceptContent(String order_code) {
		logger.debug(" S : acceptContent(String order_code) ");
		return sdao.getAcceptContent(order_code);
	}
	
	@Override
	public void changeAcceptStatus(AcceptSaveDTO ad) {
		logger.debug(" S : changeAcceptStatus(AcceptSaveDTO ad) ");
		
		// 수락수주 상태변경하는 메서드
		sdao.updateAcceptStatus(ad);
		//(AcceptSaveDTO ad)에서 수주코드, 주문코드, 상품코드, 상태 담겨있음
		// 상태가 stock인 경우 출고테이블에서 출고수량 품목코드 출고유형 업로드
		
		List<String> productList = new ArrayList<>();
		List<String> quantity = new ArrayList<>();
		List<String> salesCode = new ArrayList<>();
		AcceptSaveDTO dto = new AcceptSaveDTO();
		for(int i=0; i< ad.getSales_code().size(); i++) {
			
			
			String product_code = ad.getProduct_code().get(i); //품목코드
			String sales_quantity = ad.getSales_quantity().get(i);
			String processing_reg = ad.getProcessing_reg().get(i);
			String sales_code = ad.getSales_code().get(i);
			
			if("stock".equals(processing_reg)) {
				productList.add(product_code);
				quantity.add(sales_quantity);
			}else if("production".equals(processing_reg)) {
				salesCode.add(sales_code);
			}else if("multi".equals(processing_reg)) {
				// 현재수량 가지고 와서 
				// product_code넘기고 그걸 이용해서 현재수량 가지고 와서 
				
			}
			
			dto.setProduct_code(productList);
			dto.setSales_quantity(quantity);
			dto.setSales_code(salesCode);
			
		}
		
		
		
		
	}
	
	@Override
	public void changeProductStatus(SalesDTO sd) {
		logger.debug(" S : changeProductStatus(SalesDTO sd) ");
		sdao.changeProductStatus(sd);
	}
	
	@Override
	public void stockReg(SalesDTO sd) {
		logger.debug(" S :stockReg(SalesDTO sd)");
		sdao.stockReg(sd);
		
		// 값 변경
		sdao.changeProductStatus(sd);
	}
	
	@Override
	public void productInst(SalesDTO sd) {
		logger.debug(" S : productInst(SalesDTO sd) ");
		sdao.productInst(sd);
		// 값 변경
		sdao.changeProductStatus(sd);
		
		
	}
}
