package com.mes2.sales.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mes2.sales.domain.AcceptSaveDTO;
import com.mes2.sales.domain.PlanRegisterDTO;
import com.mes2.sales.domain.SearchDTO;
import com.mes2.sales.domain.ShippingDTO;
import com.mes2.sales.service.ShippingService;

@Controller
@RequestMapping(value = "/shipping/*")
public class ShippingController {

	private static final Logger logger = LoggerFactory.getLogger(ShippingController.class);

	@Inject
	private ShippingService pService;

	// http://localhost:8080/shipping/shipPlan
	// http://localhost:8088/shipping/shipPlan
//	@RequestMapping(value = "/shipPlan")
//	public String shippingGET() throws Exception {
//		logger.debug("/sales/shipping -> shippingGET 호출 ");
//		logger.debug("/sales/shipping.jsp 뷰페이지로 이동");
//		
//		return "/shipping/shipPlan";
//	}
	
	
	@RequestMapping(value = "/shipPlan")
	public String salesPlan(HttpSession session, Model model) {	
		//--------------------------------------//
		String user_id ="sawon4";
		session.setAttribute("user_id",user_id );
		//--------------------------------------//	
		List<ShippingDTO> list = pService.shippingList();
		ShippingDTO sdto = pService.countShipStatus();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		return "/shipping/shipPlan";
	}
	
	@RequestMapping(value = "/shipCheck" , method = RequestMethod.GET)
	public String salesPlanPost(HttpSession session, Model model, String ship_status) {	
		//--------------------------------------//
		String user_id ="sawon4";
		session.setAttribute("user_id",user_id );
		//--------------------------------------//	
		
		List<ShippingDTO> list = pService.statusList(ship_status);
		ShippingDTO sdto = pService.countShipStatus();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		return "/shipping/shipPlan";
	}
	
	@RequestMapping(value = "userShipPlanList" , method = RequestMethod.GET)
	public String userShipPlanList(HttpSession session, Model model){
		
		String user_id = (String)session.getAttribute("user_id");
		List<ShippingDTO> list = pService.UserShipPlanList(user_id);
		ShippingDTO sdto = pService.countShipStatus();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		return "/shipping/shipPlan";
	}
	
	
	@RequestMapping(value ="/planContent")
	public @ResponseBody List<ShippingDTO> planContent(String order_code){
		List<ShippingDTO> list = pService.planContent(order_code);
		return list;
	}
	
	@RequestMapping(value="/searchPlan")
	public String searchPlan(SearchDTO sed, Model model){
		
		List<ShippingDTO> list = pService.planSearch(sed);
		ShippingDTO sdto = pService.countShipStatus();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		return "/shipping/shipPlan";
	}
	
	@RequestMapping(value = "updateIdCheck")
	public @ResponseBody ShippingDTO regIdCheck(String order_code) {		
		//String user_id = (String) session.getAttribute("user_id");
		//System.out.println("!!!!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@:"+user_id);
		// 선택된 주문번호에 해당하는 user_id가져오기 
		ShippingDTO sdt = pService.getId(order_code);
		//sdt.setCheck("true");
		return sdt;
	}
	
	@RequestMapping(value = "updatePwCheck", method = RequestMethod.POST)
	public @ResponseBody ShippingDTO regPwCheck(String user_pw,String user_id,String order_code ){
		
		ShippingDTO sdt = pService.checkUpdatePw(user_id,user_pw,order_code);
		
		return sdt;
	}
	

	@RequestMapping(value = "checkSchedule", method = RequestMethod.POST)
	public @ResponseBody Integer checkSchedule(@RequestParam("scheduled_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduledDate){
		// date와 일치하는 주문번호 가져오기 
		int count = pService.getScheduleDate(scheduledDate);
		
		return count;
	}
	
	@RequestMapping(value = "updateSchedule", method = RequestMethod.POST)
	public @ResponseBody String updateSchedule(@RequestParam("scheduled_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduledDate, String order_code ){
		
		pService.updateSchedule(scheduledDate, order_code);
		return order_code;
		
	}
	
	
	@RequestMapping(value = "getOrderInfo", method = RequestMethod.POST)
	public @ResponseBody AcceptSaveDTO getOrderInfo(String order_code ){
		
		AcceptSaveDTO adt = pService.getOrderInfo(order_code);
		return adt;
	}
	
	
	@RequestMapping(value = "regShipping" , method = RequestMethod.POST)
	public @ResponseBody String regShipping(String order_code){
		
		
		return pService.shipRegister(order_code);
	}
	
	
	// http://localhost:8080/shipping/shipping
	// http://localhost:8088/shipping/shipping
	@RequestMapping(value = "/shipping")
	public String shipping(HttpSession session, Model model) {	
		//--------------------------------------//
		String user_id ="sawon4";
		session.setAttribute("user_id",user_id );
		//--------------------------------------//	
		List<ShippingDTO> list = pService.instructionList();
		ShippingDTO sdto = pService.countShipProgressing();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		return "/shipping/shipping";
	}
	
	@RequestMapping(value = "userShipList" , method = RequestMethod.GET)
	public String userShipList(HttpSession session, Model model){
		
		String user_id = (String)session.getAttribute("user_id");
		List<ShippingDTO> list = pService.userInstructionList(user_id);
		ShippingDTO sdto = pService.countShipStatus();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		return "/shipping/shipping";
	}
	

	@RequestMapping(value = "/shippingCheck")
	public String shippingCheck(HttpSession session, Model model, String progress_status) {	
		//--------------------------------------//
		String user_id ="sawon4";
		session.setAttribute("user_id",user_id );
		//--------------------------------------//	
		List<ShippingDTO> list = pService.progressList(progress_status);
		ShippingDTO sdto = pService.countShipProgressing();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		return "/shipping/shipping";
	}
	

	@RequestMapping(value ="/shipContent")
	public @ResponseBody List<ShippingDTO> shipContent(String order_code){
		List<ShippingDTO> list = pService.shipContent(order_code);
		return list;
	}
	
	
	@RequestMapping(value = "changeShipSchedule", method = RequestMethod.POST)
	public @ResponseBody String changeShipSchedule(@RequestParam("scheduled_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduledDate, String order_code ){
		
		pService.updateSchedule(scheduledDate, order_code);
		pService.updateShipDate(scheduledDate, order_code);
		return order_code;
		
	} 
	
	@RequestMapping(value = "/searchShipping" , method = RequestMethod.POST)
	public String searchShipping(SearchDTO sed, Model model) {	
        	
		List<ShippingDTO> list = pService.shippingSearch(sed);
		ShippingDTO sdto = pService.countShipProgressing();
		model.addAttribute("status", sdto);
		model.addAttribute("list", list);
		return "/shipping/shipping";
	}
	

}
