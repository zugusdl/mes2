package com.mes2.sales.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.sales.domain.SalesDTO;
import com.mes2.sales.service.SalesService;

@Controller
@RequestMapping(value = "/sales/*")
public class SalesController {

	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);

	@Inject
	private SalesService sService;

	// http://localhost:8088/sales/sales
//	@GetMapping(value = "/sales")
//	public String salesGET() throws Exception {
//		logger.debug("/sales/sales -> salesGET 호출 ");
//		logger.debug("/sales/sales.jsp 뷰페이지로 이동");
//		
//		return "/sales/sales";
//	}
	// http://localhost:8088/sales/salesPlan
	
	@RequestMapping(value = "/salesPlan")
	public String salesPlan() {	
		return "/sales/sales";
	}
	
	@RequestMapping(value = "salesPlanList", method=RequestMethod.GET)
	public @ResponseBody List<SalesDTO> PlanListGet() {
		
		List<SalesDTO> list = sService.salesPlanList();	
		return list;
	}
	
	@RequestMapping(value ="planContent")
	public @ResponseBody List<SalesDTO> planContent(String order_code){
		
		List<SalesDTO> list = sService.PlanContent(order_code);
		return list;
	}
	
	@RequestMapping(value="rejectSales")
	public @ResponseBody List<SalesDTO> rejectSales(String order_code){
		
		// dto받아서 where로 삭제하고 다시 list로드해서 주기 
		sService.rejectSales(order_code);
		List<SalesDTO> list = sService.PlanContent(order_code);
		return list;
	}
	
}
