package com.mes2.materials.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes2.materials.service.InService;

@Controller
@RequestMapping(value = "/materials/*")
public class InController {

	private static final Logger logger = LoggerFactory.getLogger(InController.class);

	@Inject
	private InService iService;

	// http://localhost:8080/materials/in
	@GetMapping(value = "/in")
	public String inGET() throws Exception {
		logger.debug("/materials/in -> inGET 호출 ");
		logger.debug("/materials/in.jsp 뷰페이지로 이동");
		
		return "/materials/in";
	}

}
