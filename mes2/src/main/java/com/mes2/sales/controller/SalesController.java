package com.mes2.sales.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes2.sales.service.SalesService;

@Controller
@RequestMapping(value = "/sales/*")
public class SalesController {

	private static final Logger logger = LoggerFactory.getLogger(SalesController.class);

	@Inject
	private SalesService sService;

	// http://localhost:8080/sales/sales
	@GetMapping(value = "/sales")
	public String salesGET() throws Exception {
		logger.debug("/sales/sales -> salesGET 호출 ");
		logger.debug("/sales/sales.jsp 뷰페이지로 이동");
		
		return "/sales/sales";
	}

}
