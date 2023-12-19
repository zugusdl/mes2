package com.mes2.platform.controller;

import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mes2.platform.domain.mdbDTO;
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
			session.setAttribute("mdb_code", mdbDTO.getCompany_code());
			return "redirect:/platform/main";
		}
		
		rttr.addFlashAttribute("result", "loginFail");
		
		return "redirect:/platform/login";
	}
	
	@GetMapping(value="/main")
	public void main() {
		
	}
	
	@GetMapping(value="/insertOrder")
	public void insertOrder() {
		logger.debug("insertOrder() 호출");
	}
	
}
