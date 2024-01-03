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
	
//	@RequestMapping(value = "/salesPlan")
//	public String salesPlan() {	
//		return "/sales/sales";
//	}
	// http://localhost:8080/sales/salesPlan
	// http://localhost:8088/sales/salesPlan
	//@RequestMapping(value = "/salesPlanTest")
	@RequestMapping(value = "/salesPlan")
	public String salesPlanTest(HttpSession session, Model model) {	
		//--------------------------------------//
		String user_id ="sawon4";
		session.setAttribute("user_id",user_id );
		//--------------------------------------//
		String sales_status = "requested";
		List<SalesDTO> list = sService.salesList(sales_status);	
		model.addAttribute("list", list);
		
		SalesDTO sdt = sService.salesPlanCnt();
		model.addAttribute("status", sdt);
		
		return "/sales/salesPlan";
	}
	
	@RequestMapping(value = "/newSalesPlan")
	public String newSalesPlan(HttpSession session, Model model) {	
		//--------------------------------------//
		String user_id ="sawon4";
		session.setAttribute("user_id",user_id );
		//--------------------------------------//
		
		List<SalesDTO> list = sService.getNewSales();
		model.addAttribute("list", list);
		return "/sales/salesPlan";
	}
	
	
	
	@RequestMapping(value = "salesPlanList", method=RequestMethod.GET)
	public @ResponseBody List<SalesDTO> PlanListGet() {
		String sales_status = "requested";
		List<SalesDTO> list = sService.salesList(sales_status);	
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
		
		return "redirect:/sales/salesPlan";
		
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
		sService.insertShippingPlan(pdto);
		return "redirect:/sales/salesPlan";
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
		//String sales_status = "accept";
		List<SalesDTO> list = sService.acceptList();	
		//List<SalesDTO> list = sService.salesAcceptList();
		SalesDTO status = sService.proCnt();
		model.addAttribute("status", status);
		model.addAttribute("list", list);
		return "/sales/salesAccept";
	}
	
	@RequestMapping (value = "/completePro" )
	public String completePro(Model model) {	
		//--------------------------------------//
		
		//--------------------------------------//

		List<SalesDTO> list = sService.completeList();
		SalesDTO status = sService.proCnt();
		model.addAttribute("status", status);
		model.addAttribute("list", list);
		return "/sales/salesAccept";
	}
	
	@RequestMapping (value = "/waitPro" )
	public String waitPro(Model model) {	
		//--------------------------------------//
		
		//--------------------------------------//

		List<SalesDTO> list = sService.waitList();
		SalesDTO status = sService.proCnt();
		model.addAttribute("status", status);
		model.addAttribute("list", list);
		return "/sales/salesAccept";
	}
	
	@RequestMapping (value = "/newPro" )
	public String newPro(Model model) {	
		//--------------------------------------//
		
		
		//--------------------------------------//

		List<SalesDTO> list = sService.newAcceptList();
		SalesDTO status = sService.proCnt();
		model.addAttribute("status", status);
		model.addAttribute("list", list);
		return "/sales/salesAccept";
	}
	
	@RequestMapping (value = "/userPro" )
	public String userPro(HttpSession session, Model model) {	
		//--------------------------------------//
		
		String user_id = (String)session.getAttribute("user_id");
		//--------------------------------------//

		List<SalesDTO> list = sService.UserAccept(user_id);
		SalesDTO status = sService.proCnt();
		model.addAttribute("status", status);
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
		// 주문번호 수주번호 상품코드 수주량(sales_quantity) 처리등록여부
		//dto를 받아서 processing_reg가 뭐인지에 따라서 재고출하면 재고출하업데이트
		// 생산계획이면 생산계획쪽에 넣어주기 
		//재고출하면 재고출하업데이트
		// 복합이면 ... ㅗ^_^ㅗ 
		String order_code = dto.getOrder_code();
		if(dto != null && dto.getProcessing_reg().equals("stock")) {
			// 출고테이블 - 출고수량, 품목코드, 출고유형(S)입력
			// 수주량만큼 창고에서 수량빼주기 (상품번호 가져가면 됨)
			// dto.setProduct_status("complete");
			 dto.setProduct_status("progressing");
			// 1. stock_quantity = sales_quantity
			// sales_quantity의 값을 stock_quantity에 넣어주기 
			 // 수주량 전체가 재고에 있어서 주문 넣는 경우 
			 // 인자로 가져온 수주량을 재고량으로 넣어주기 (지시)
			dto.setStock_quantity(dto.getSales_quantity());
			// 2. stock_quantity > sales_quantity
			// sales_quantity의 값을 stock_quantity에 넣어주고 stock_quantity로 계산하기 (sales_quantity로 받으면 안됨)
			sService.stockReg(dto);
			sService.updateStockQuan(dto);
			
		}
		if(dto != null && dto.getProcessing_reg().equals("production")) {
			// 전체 부족인 경우 
			
			dto.setProduct_status("progressing");
			dto.setLack_quantity(dto.getSales_quantity());
			sService.productInst(dto);
			
			dto.setStock_quantity(dto.getSales_quantity());
			sService.stockReg(dto); // 이 경우는 재고를 나중에 수아씨가 빼줘야함
			//sService.updateStockQuan(dto); // 수량뺄게없음
		}
		if(dto.getProcessing_reg().equals("multi")) {
			
			// 4. stock_quantity < sales_quantity 
			dto.setProduct_status("progressing");
			// 현재고 가져오기 
			int stock_quantity = sService.stockQuantity(dto).getStock_quantity();
			
			// 부족재고 가져오기 
			int lack_quantity =(dto.getSales_quantity() - stock_quantity);
			// 자재팀한테는 stock_quantity 만큼 준비지시 (무조건)  이 만큼 내가 빼주고 
			// 생산팀한테는 lack_quantity 만큼 생산하라고 알리기	 이 만큼은 수아씨가 빼줘야함	  그럼 내가 이 값을 줘야할듯 
			//dto.setSales_quantity(stock_quantity);
			dto.setStock_quantity(stock_quantity);
			dto.setLack_quantity(lack_quantity);
			// 수주량만큼 창고에서 수량빼주기 (상품번호 가져가면 됨)
			
			sService.stockReg(dto);
			sService.updateStockQuan(dto);
			sService.productInst(dto);
		}
		if(dto.getProcessing_reg().equals("N")) {
			// 아무 동작이 필요하지 않은 경우
		}
	
		
		return order_code;
	}
	
	
	
	
	
	}
