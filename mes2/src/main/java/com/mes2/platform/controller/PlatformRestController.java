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

import com.mes2.platform.domain.MdpDTO;
import com.mes2.platform.service.PlatformService;
import com.mes2.platform.service.PlatformServiceImpl;

// http://localhost:8088/platform/login
@RestController // RestController =  (@ResponseBody + @Controller)
@RequestMapping(value="/restPlatform/*")
public class PlatformRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(PlatformRestController.class);
	
	@Inject
	private PlatformService pService;
	
	@PostMapping(value ="/registProduct")
	public MdpDTO registProduct(@RequestParam("product_code") String product_code) throws Exception {
		logger.debug("registProduct() 호출");
		logger.debug("product_code" + product_code);
		MdpDTO mdpDTO = pService.registProduct(product_code);
		return mdpDTO;
	}
	
	@PostMapping(value = "/receiveDelivery")
	public void receiveDelivery(@RequestParam("order_code") String order_code) throws Exception {
		logger.debug("receiveDelivery() 호출");
		pService.receiveDelivery(order_code);
	}
	
}
