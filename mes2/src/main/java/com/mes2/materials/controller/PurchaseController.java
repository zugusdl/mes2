package com.mes2.materials.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes2.materials.service.PurchaseService;

@Controller
@RequestMapping(value = "/materials/*")
public class PurchaseController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

	@Inject
	private PurchaseService pService;

	// http://localhost:8088/materials/purchase
	@GetMapping(value = "/purchase")
	public String purchaseGET() throws Exception {
		logger.debug("/materials/purchase -> purchaseGET 호출 ");
		logger.debug("/materials/purchase.jsp 뷰페이지로 이동");
	
		return "/materials/purchase";
	}
}
