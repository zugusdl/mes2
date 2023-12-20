package com.mes2.platform.controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mes2.platform.domain.mdbDTO;
import com.mes2.platform.domain.mdpDTO;
import com.mes2.platform.service.PlatformService;
import com.mes2.platform.service.PlatformServiceImpl;

// http://localhost:8088/platform/login
@Controller
@RequestMapping(value="/platform/*")
public class PlatformController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);
	
	@Inject
	private PlatformService pService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("result") String result) throws Exception {
		logger.debug("platform.loginGET() 호출");
	}
	
	@PostMapping(value="/login")
	public String loginPOST(mdbDTO mdbDTO, HttpSession session, RedirectAttributes rttr) throws Exception {
		logger.debug("platform.loginPOST() 호출");
		
		logger.debug("mdbDTO: " + mdbDTO);
		
		mdbDTO mdto = pService.customerLogin(mdbDTO);
		
		if(mdto != null) {
			session.setAttribute("company_code", mdbDTO.getCompany_code());
			return "redirect:/platform/orderList";
		}

		rttr.addFlashAttribute("result", "loginFail");
		
		return "redirect:/platform/login";
	}

	@GetMapping(value="/orderList")
	public String orderList(HttpSession session) throws Exception {
		String company_code = (String) session.getAttribute("company_code");
		
		logger.debug("company_code: " + company_code);
		
		if (company_code == null) {
			return "redirect:/platform/login";
		}
		
		return "/platform/orderList";
	}
	
	@GetMapping(value="/insertOrder")
	public void insertOrder() throws Exception {
		logger.debug("insertOrder() 호출");
	}
	
	@GetMapping(value="/searchList")
	public void searchList(Model model) throws Exception {
		logger.debug("searchList() 호출");
	}
	
	@PostMapping(value="/searchList")
	public void searchList(@RequestParam("searchType") String searchType, @RequestParam("search") String search, Model model) throws Exception {
		logger.debug("searchList() 호출");
		List<mdpDTO> mdpDTO = pService.inqueryProduct(searchType, search);
		model.addAttribute("mdpDTO", mdpDTO);
	}
	
	@PostMapping(value="/insertProduct")
	public String insertProduct() throws Exception {
		return "";
	}
}
