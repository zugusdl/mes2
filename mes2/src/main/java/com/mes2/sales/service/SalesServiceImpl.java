package com.mes2.sales.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mes2.platform.service.PlatformServiceImpl;
import com.mes2.sales.domain.SalesDTO;
import com.mes2.sales.persistence.SalesDAO;

@Service
public class SalesServiceImpl implements SalesService {

	private static final Logger logger = LoggerFactory.getLogger(PlatformServiceImpl.class);
	
	@Inject
	private SalesDAO sdao;
	
	@Override
	public List<SalesDTO> salesPlanList() {
		logger.debug(" S : salesList() ");
		return sdao.getPlanSalesList();
	}
	
	@Override
	public List<SalesDTO> PlanContent(String order_code) {
		logger.debug(" S : PlanContent(String order_code) ");
		return sdao.getPlanContent(order_code);
	}
	
	@Override
	public void rejectSales(String order_code) {
		logger.debug(" S : rejectSales(String order_code) ");
		sdao.rejectSales(order_code);
		
	}
}
