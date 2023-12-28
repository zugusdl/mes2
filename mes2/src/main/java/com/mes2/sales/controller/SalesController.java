package com.mes2.sales.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;
import com.mes2.sales.domain.SearchDTO;
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
	// http://localhost:8080/sales/salesPlanTest
	// http://localhost:8088/sales/salesPlanTest
	@RequestMapping(value = "/salesPlanTest")
	public String salesPlanTest(HttpSession session, Model model) {	
		//--------------------------------------//
		String user_id ="sawon4";
		session.setAttribute("user_id",user_id );
		//--------------------------------------//
		//String status = "requested";
		List<SalesDTO> list = sService.salesPlanList();	
		model.addAttribute("list", list);
		return "/sales/salesPlan";
	}
	
	@RequestMapping(value = "salesPlanList", method=RequestMethod.GET)
	public @ResponseBody List<SalesDTO> PlanListGet() {
		//String status = "requested";
		List<SalesDTO> list = sService.salesPlanList();	
		return list;
	}
	
	@RequestMapping(value ="planContent")
	public @ResponseBody List<SalesDTO> planContent(String order_code){
		
		List<SalesDTO> list = sService.PlanContent(order_code);
		return list;
	}
	
	@RequestMapping(value="rejectSales", method = RequestMethod.POST)
	public String rejectSales(@RequestParam("order_code") List<String> odList){
		
		
		sService.rejectSales(odList);
		
		return "redirect:/sales/salesPlanTest";
		
	}
	
	@RequestMapping(value = "stockCheck")
	public @ResponseBody SalesDTO stockCheck(SalesDTO sd) {
		SalesDTO dto = sService.stockQuantity(sd);
		return dto;
	}
	
	@RequestMapping(value="searchPlan")
	public String searchPlan(SearchDTO sed, Model model){
		
		String sales_status = "requested";
		sed.setSales_status(sales_status);
		List<SalesDTO> list =sService.searchListPlan(sed);
		model.addAttribute("list", list);
		return "/sales/salesPlan";
	}
	
	@RequestMapping(value="searchAccept")
	public String searchAccept(SearchDTO sed, Model model){
		
		String sales_status = "accept";
		sed.setSales_status(sales_status);
		List<SalesDTO> list =sService.searchListPlan(sed);
		model.addAttribute("list", list);
		return "/sales/salesAccept";
	}
	
	@RequestMapping(value="planRegister", method = RequestMethod.POST)
	public String planRegister(PlanRegisterDTO pdto ) {
		
		sService.registerPlan(pdto);
		List<SalesDTO> list = sService.getProdctCode(pdto.getOrder_code());
		sService.makeSalesCode(list);
		return "redirect:/sales/salesPlanTest";
	}
	
	@RequestMapping(value = "regIdCheck")
	public @ResponseBody String regIdCheck(HttpSession session) {
		
		String user_id = (String) session.getAttribute("user_id");
		System.out.println("!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+user_id);
		return user_id;
	}
	
	@RequestMapping(value = "regPwCheck", method = RequestMethod.POST)
	public @ResponseBody String regPwCheck(String user_pw,HttpSession session){
		String user_id = (String) session.getAttribute("user_id");
		String check = sService.checkRegPw(user_id,user_pw);
		return check;
	}
	
	
	// http://localhost:8080/sales/salesAccept
	// http://localhost:8088/sales/salesAccept
	@RequestMapping (value = "/salesAccept" )
	public String salesAccept(HttpSession session, Model model) {	
		//--------------------------------------//
		String user_id ="sawon4";
		session.setAttribute("user_id",user_id );
		//--------------------------------------//
		List<SalesDTO> list = sService.salesAcceptList();
		model.addAttribute("list", list);
		return "/sales/salesAccept";
	}
	
	
	@RequestMapping(value ="acceptContent")
	public @ResponseBody List<SalesDTO> acceptContent(String order_code){
		
		List<SalesDTO> list = sService.acceptContent(order_code);
		return list;
	}
	
	@RequestMapping(value = "acceptSave")
		public @ResponseBody String acceptSave(SalesDTO dto){
		// 주문번호 수주번호 상품번호 수주량 처리등록여부
		//dto를 받아서 processing_reg가 뭐인지에 따라서 재고출하면 재고출하업데이트
		// 생산계획이면 생산계획쪽에 넣어주기 
		//재고출하면 재고출하업데이트
		// 복합이면 ... ㅗ^_^ㅗ 
		String order_code = dto.getOrder_code();
		if(dto != null && dto.getProcessing_reg().equals("stock")) {
			// 출고테이블 - 출고수량, 품목코드, 출고유형(S)입력
			// 수주량만큼 창고에서 수량빼주기 (상품번호 가져가면 됨)
			dto.setProduct_status("complete");
			// 1. stock_quantity = sales_quantity
			// sales_quantity의 값을 stock_quantity에 넣어주기 
			dto.setStock_quantity(dto.getSales_quantity());
			// 2. stock_quantity > sales_quantity
			// sales_quantity의 값을 stock_quantity에 넣어주고 stock_quantity로 계산하기 (sales_quantity로 받으면 안됨)
			sService.stockReg(dto);
			
		}
		if(dto != null && dto.getProcessing_reg().equals("production")) {
			
			dto.setProduct_status("progressing");
			dto.setLack_quantity(dto.getSales_quantity());
			sService.productInst(dto);
		}
		if(dto.getProcessing_reg().equals("multi")) {
			
			// 4. stock_quantity < sales_quantity 
			dto.setProduct_status("progressing");
			// 현재고 가져오기 
			int stock_quantity = sService.stockQuantity(dto).getStock_quantity();
			// 부족재고 가져오기 
			int lack_quantity =(dto.getSales_quantity() - stock_quantity);
			// 자재팀한테는 stock_quantity 만큼 준비지시 (무조건)
			// 생산팀한테는 lack_quantity 만큼 생산하라고 알리기		 
			dto.setSales_quantity(stock_quantity);
			dto.setLack_quantity(lack_quantity);
			// 수주량만큼 창고에서 수량빼주기 (상품번호 가져가면 됨)
			
			sService.stockReg(dto);
			sService.productInst(dto);
		}
		if(dto.getProcessing_reg().equals("N")) {
			// 아무 동작이 필요하지 않은 경우
		}
	
		
		return order_code;
	}
	
	
	
	}
