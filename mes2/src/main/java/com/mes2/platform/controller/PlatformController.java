package com.mes2.platform.controller;

import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mes2.platform.domain.mdbDTO;
import com.mes2.platform.service.PlatformService;
import com.mes2.platform.service.PlatformServiceImpl;

// http://localhost:8088/login
@Controller
@RequestMapping(value="/platform/*")
public class PlatformController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformController.class);
	
	@Inject
	private PlatformService pService;
	
	@RequestMapping(value="/platform/login", method=RequestMethod.GET)
	public void loginGET() throws Exception {
		logger.debug("platform.loginGET() 호출");
	}
	
	@PostMapping(value="/platform/login")
	public String loginPOST(mdbDTO mdbDTO, HttpSession session) throws Exception {
		logger.debug("platform.loginPOST() 호출");
		
		mdbDTO mdto = pService.customerLogin(mdbDTO);
		
		return "redirect:/platform/main";
	}
	
}
