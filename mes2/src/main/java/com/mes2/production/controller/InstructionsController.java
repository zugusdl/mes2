package com.mes2.production.controller;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.service.InstructionsService;
import com.mes2.production.vo.InstructionsState;

@Controller
@RequestMapping("/instructions")
public class InstructionsController {

	@Autowired
	private InstructionsService instructionsService;
	
	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	
	@GetMapping("/start")
	public String startGET() {
		
		InstructionsDTO instructsDto = new InstructionsDTO();
		instructsDto.setCode("20231219A0001");
		instructsDto.setLine(1);
		instructsDto.setMdpCode("A1111");
		instructsDto.setType("F");
		instructsDto.setSoiCode("S1111");
		instructsDto.setState("S");
		
		instructionsService.saveInstructions(instructsDto);
		
		return "ok";
	}
	
	//http://localhost:8088/instructions/detail/20231219A0001
	@GetMapping("/detail/{code}")
	public String detailGET(@PathVariable("code")String code) {
		
		//
		return null;
	}
	
	@GetMapping("/search")
	public String searchGET(Model model,@ModelAttribute(value = "startDate") String startDate,
			@ModelAttribute(value="endDate") String endDate, 
			@ModelAttribute(value = "code") String code,
			@ModelAttribute(value="state") InstructionsState state ) {
		
		
		InstructionsSearchParam param = new InstructionsSearchParam();
		
		if(startDate==null || startDate.equals("")) {
			  param.setStartDate(null);
		}else {
			param.setStartDate(Date.valueOf(startDate));
		}
		
		if(endDate==null || endDate.equals("")) {
			  param.setStartDate(null);
		}else {
			param.setEndDate(Date.valueOf(endDate));
		}
		param.setCode(code);
		param.setState(state);
		
		log.debug("입력받은 state"+state.getClass());
		
		
		
		return "/instructions/main";
	}
	

}
