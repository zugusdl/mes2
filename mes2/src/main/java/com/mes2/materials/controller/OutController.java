package com.mes2.materials.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.materials.domain.OutDTO;
import com.mes2.materials.service.OutService;

@Controller
@RequestMapping(value = "/materials/*")
public class OutController {

	private static final Logger logger = LoggerFactory.getLogger(OutController.class);

	@Inject
	private OutService oService;

	// http://localhost:8088/materials/outList

	// 출고 목록 리스트 - GET
	@GetMapping(value = "/outList")
	public void outListGET(Model model, OutDTO odto) throws Exception {
		logger.debug("outListGET() 호출 ");

		model.addAttribute("oList", oService.getOutList());
	}
	
	// 출고 상세 리스트 - GET
	@GetMapping(value="/outDetail")
	public void outDetailGET(@RequestParam("out_index") String out_index, @RequestParam("out_code") String out_code, Model model) throws Exception {
		logger.debug("outDetailGET() 호출");
		logger.debug(out_index, out_code);
		
		if(!out_code.equals(null)) {
			OutDTO outDTO = oService.getOutDetail(out_index);
			model.addAttribute("outDTO", outDTO);
		}
		
		if(out_code.equals(null)) {
			model.addAttribute("out_index", out_index);
		}
		
	}
	
}
