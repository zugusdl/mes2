package com.mes2.materials.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mes2.materials.service.OutService;

@Controller
@RequestMapping(value = "/materials/*")
public class OutController {
	private static final Logger logger = LoggerFactory.getLogger(InController.class);

	@Inject
	private OutService oService;

	// http://localhost:8088/materials/out
	@GetMapping(value = "/out")
	public String outGET() throws Exception {
		logger.debug("/materials/out -> outGET 호출 ");
		logger.debug("/materials/out.jsp 뷰페이지로 이동");
		
		return "/materials/out";
	}
}

