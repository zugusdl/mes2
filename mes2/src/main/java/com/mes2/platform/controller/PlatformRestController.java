package com.mes2.platform.controller;

import org.slf4j.LoggerFactory;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mes2.platform.domain.mdbDTO;
import com.mes2.platform.domain.mdpDTO;
import com.mes2.platform.service.PlatformService;
import com.mes2.platform.service.PlatformServiceImpl;

// http://localhost:8088/platform/login
@RestController // RestController =  (@ResponseBody + @Controller)
@RequestMapping(value="/restPlatform/*")
public class PlatformRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformRestController.class);
	
	@Inject
	private PlatformService pService;
	
	@RequestMapping(value="/inqueryProduct", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	public List<mdpDTO> inqueryProduct(@RequestParam("searchType") String searchType, @RequestParam("search") String search) throws Exception {
		logger.debug("inqueryProduct() 호출");
		List<mdpDTO> mdpDTO = pService.inqueryProduct(searchType, search);
		return mdpDTO;
	}
	
}
