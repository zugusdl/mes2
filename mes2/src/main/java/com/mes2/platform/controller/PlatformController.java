package com.mes2.platform.controller;

import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mes2.platform.domain.MdbDTO;
import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.domain.SoiDTO;
import com.mes2.platform.domain.SopDTO;
import com.mes2.platform.domain.orderRequestDTO;
import com.mes2.platform.service.PlatformService;
import com.mes2.platform.service.PlatformServiceImpl;

// http://localhost:8088/platform/login
@Controller
@RequestMapping(value="/platform/*")
public class PlatformController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);
	
	@Inject
	private PlatformService pService;
	
	// 로그인 화면 호출
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("result") String result) throws Exception {
		logger.debug("platform.loginGET() 호출");
	}
	
	// 로그인하고 메인페이지로 이동
	@PostMapping(value="/login")
	public String loginPOST(MdbDTO mdbDTO, HttpSession session, RedirectAttributes rttr) throws Exception {
		logger.debug("platform.loginPOST() 호출");
		
		logger.debug("mdbDTO: " + mdbDTO);
		
		MdbDTO mdto = pService.customerLogin(mdbDTO);
		
		if(mdto != null) {
			session.setAttribute("company_code", mdbDTO.getCompany_code());
			return "redirect:/platform/orderList";
		}

		rttr.addFlashAttribute("result", "loginFail");
		
		return "redirect:/platform/login";
	}

	// 발주(주문) 목록 페이지
	@GetMapping(value="/orderList")
	public String orderListGET(HttpSession session) throws Exception {
		String company_code = (String) session.getAttribute("company_code");
		
		if (company_code == null) {
			return "redirect:/platform/login";
		}
		
		return "/platform/orderList";
	}

	//http://localhost:8080/platform//insertOrder
	// 발주(주문) 추가 페이지
	@GetMapping(value="/insertOrder")
	public void insertOrderGET(Model model) throws Exception {
		logger.debug("insertOrderGET() 호출");
		
		// 현재 날짜
		Calendar minDay = Calendar.getInstance();
		Calendar maxDay = Calendar.getInstance();
		minDay.add(Calendar.DATE, 14);
		maxDay.add(Calendar.DATE, 44);
		Date minDate = new Date(minDay.getTimeInMillis());
		Date maxDate = new Date(maxDay.getTimeInMillis());
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String formatMinDate = sdf.format(minDate);
		String formatMaxDate = sdf.format(maxDate);
		
		model.addAttribute("minDay", formatMinDate);
		model.addAttribute("maxDay", formatMaxDate);
	}
	
	// 발주(주문) 추가 페이지
	@PostMapping(value="/insertOrder")
	public String insertOrderPOST(@RequestBody orderRequestDTO orDTO, HttpSession session) throws Exception {
		logger.debug("insertOrderPOST() 호출");
		logger.debug("@@@@orDTO" + orDTO.toString());
		pService.insertOrder(orDTO, session);
		return "redirect:/platform/orderList";
	}
	
	// 품목 추가 페이지에서 품목 찾기
	@GetMapping(value="/searchList")
	public void searchListGET(Model model) throws Exception {
		logger.debug("searchListGET() 호출");
	}
	
	// 품목 추가 페이지에서 검색
	@PostMapping(value="/searchList")
	public void searchListPOST(@RequestParam("searchType") String searchType, @RequestParam("search") String search, Model model) throws Exception {
		logger.debug("searchListPOST() 호출");
		List<MdpDTO> mdpDTO = pService.inqueryProduct(searchType, search);
		model.addAttribute("mdpDTO", mdpDTO);
	}
}
