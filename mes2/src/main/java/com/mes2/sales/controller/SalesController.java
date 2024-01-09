package com.mes2.sales.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.Criteria;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SalesDTO;

import com.mes2.sales.domain.pageMaking;
import com.mes2.sales.service.SalesService;

@Controller
@RequestMapping(value = "/sales/*")
public class SalesController {

	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);

	@Inject
	private SalesService sService;

	// http://localhost:8080/sales/salesPlan
	// http://localhost:8088/sales/salesPlan
	@RequestMapping(value = "/salesPlan")
	public String salesPlan(HttpSession session, Model model, Criteria cri) {	
		//--------------------------------------//
		String id ="sawon8";
		session.setAttribute("id",id );
		//--------------------------------------//
		cri.setSales_status("requested");
		List<SalesDTO> list = sService.salesList(cri);	
		model.addAttribute("list", list);
		
		SalesDTO sdt = sService.salesPlanCnt();
		model.addAttribute("status", sdt);
		
		pageMaking pm = new pageMaking();
		pm.setCri(cri);
		pm.setTotalCount(sService.totalCount(cri));
		model.addAttribute("pm", pm);
		return "/sales/salesPlan";
	}
	
//	@RequestMapping(value = "/newSalesPlan")
//	public String newSalesPlan(HttpSession session, Model model) {	
//		//--------------------------------------//
//		String id ="sawon4";
//		session.setAttribute("id",id );
//		//--------------------------------------//
//		
//		List<SalesDTO> list = sService.getNewSales();
//		model.addAttribute("list", list);
//		return "/sales/salesPlan";
//	}
//	
	
	
//	@RequestMapping(value = "salesPlanList", method=RequestMethod.GET)
//	public @ResponseBody List<SalesDTO> PlanListGet(Criteria cri) {
//		//String sales_status = "requested";
//		List<SalesDTO> list = sService.salesList(cri);	
//		return list;
//	}
	
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
	
//	@RequestMapping(value="searchPlan")
//	public String searchPlan(SearchDTO sed, Model model){
//		
//		String sales_status = "requested";
//		sed.setSales_status(sales_status);
//		List<SalesDTO> list =sService.searchListPlan(sed);
//		model.addAttribute("list", list);
//		return "/sales/salesPlan";
//	}
	
//	@RequestMapping(value="searchAccept")
//	public String searchAccept(SearchDTO sed, Model model){
//		
//		String sales_status = "accept";
//		sed.setSales_status(sales_status);
//		List<SalesDTO> list =sService.searchListPlan(sed);
//		SalesDTO status = sService.proCnt();
//		model.addAttribute("status", status);
//		model.addAttribute("list", list);
//		return "/sales/salesAccept";
//	}
	
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
		String id = (String) session.getAttribute("id");
		return id;
	}
	
	@RequestMapping(value = "regPwCheck", method = RequestMethod.POST)
	public @ResponseBody String regPwCheck(String user_pw,HttpSession session){
		String user_id = (String) session.getAttribute("id");
		String check = sService.checkRegPw(user_id,user_pw);
		return check;
	}
	
	
	// http://localhost:8080/sales/salesAccept
	// http://localhost:8088/sales/salesAccept
	
	
	@RequestMapping (value = "/salesAccept" )
	public String salesAccept(HttpSession session, Model model,Criteria cri,@RequestParam(name = "user", required = false) String user) {	
		//--------------------------------------//
				String id ="sawon8";
				session.setAttribute("id",id );
				//--------------------------------------//
				String user_id = cri.getUserId();
				if (user != null && user.equals("true")) {
			        user_id = (String) session.getAttribute("id");
		         }
		        cri.setUserId(user_id);
		
		cri.setSales_status("accept");
		List<SalesDTO> list = sService.salesList(cri);	
		
		SalesDTO status = sService.proCnt();
		pageMaking pm = new pageMaking();
		pm.setCri(cri);
		pm.setTotalCount(sService.totalCount(cri));
		model.addAttribute("pm", pm);
		
		model.addAttribute("status", status);
		model.addAttribute("list", list);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+cri);
		return "/sales/salesAccept";
	}
	
//	@RequestMapping (value = "/completePro" )
//	public String completePro(Model model) {			
//		
//		SalesDTO status = sService.proCnt();
//		List<SalesDTO> list = sService.instructionList("Y");
//		model.addAttribute("status", status);
//		model.addAttribute("list", list);
//		
//		return "/sales/salesAccept";
//	}
	
//	@RequestMapping (value = "/waitPro" )
//	public String waitPro(Model model) {			
//		//List<SalesDTO> list = sService.waitList();
//		SalesDTO status = sService.proCnt();
//		List<SalesDTO> list = sService.instructionList("N");
//		model.addAttribute("status", status);
//		model.addAttribute("list", list);
//		return "/sales/salesAccept";
//	}
	
//	@RequestMapping (value = "/newPro" )
//	public String newPro(Model model) {			
//		List<SalesDTO> list = sService.newAcceptList();
//		SalesDTO status = sService.proCnt();
//		model.addAttribute("status", status);
//		model.addAttribute("list", list);
//		return "/sales/salesAccept";
//	}
//	
//	@RequestMapping (value = "/userPro" )
//	public String userPro(HttpSession session, Model model) {			
//		String id = (String)session.getAttribute("id");		
//		List<SalesDTO> list = sService.UserAccept(id);
//		SalesDTO status = sService.proCnt();
//		model.addAttribute("status", status);
//		model.addAttribute("list", list);
//		return "/sales/salesAccept";
//	}
	@RequestMapping(value ="acceptContent")
	public @ResponseBody List<SalesDTO> acceptContent(String order_code){
		
		List<SalesDTO> list = sService.acceptContent(order_code);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+list);
		return list;
	}
	
	@RequestMapping(value = "/acceptSave" , method = RequestMethod.POST)
		public @ResponseBody String acceptSave(@RequestBody List<SalesDTO> list){
		// 주문번호 수주번호 상품코드 수주량(sales_quantity) 처리등록여부
		
		return sService.instructSales(list);
	}
	
	
	@RequestMapping(value = "getOrderInfo", method = RequestMethod.POST)
	public @ResponseBody AcceptSaveDTO getOrderInfo(String order_code ){
		return  sService.orderInfo(order_code);
	}
	
	@RequestMapping(value = "orderPlanInfo", method = RequestMethod.POST)
	public @ResponseBody AcceptSaveDTO orderPlanInfo(String order_code ){
		return  sService.orderPlanInfo(order_code);
	}
	
	@RequestMapping(value = "getRegUser", method = RequestMethod.POST)
	public @ResponseBody SalesDTO  getRegUser(String order_code ){
		
		return  sService.getRegUser(order_code);
	}
	
	@RequestMapping(value = "getRegPw", method = RequestMethod.POST)
	public @ResponseBody String  getRegPw(String user_id, String user_pw){
		
		return  sService.checkRegPw(user_id, user_pw);
	}
	
	}
