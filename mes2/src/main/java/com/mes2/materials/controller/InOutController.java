package com.mes2.materials.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes2.materials.service.InOutService;

@Controller
@RequestMapping(value = "/materials/*")
public class InOutController {

	private static final Logger logger = LoggerFactory.getLogger(InOutController.class);

	@Inject
	private InOutService ioService;

	// http://localhost:8080/materials/inAout
	@GetMapping(value = "/inAout")
	public String inGET() throws Exception {
		logger.debug("/materials/in -> inAoutGET 호출 ");
		logger.debug("/materials/in.jsp 뷰페이지로 이동");
		
		return "/materials/inAout";
	}


	

}
