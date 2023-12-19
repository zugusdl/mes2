package com.mes2.production.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.persistence.InstructionsDAO;

@RestController
@RequestMapping("/instructions")
public class InstructionsRestController {

	@Autowired
	private InstructionsDAO instructionsDAO;
	
	@GetMapping("/start")
	public String startGET() {
		
		InstructionsDTO instructsDto = new InstructionsDTO();
		instructsDto.setCode("20231219A0001");
		instructsDto.setLine(1);
		instructsDto.setMdpCode("A1111");
		instructsDto.setType("F");
		instructsDto.setSoiCode("S1111");
		instructsDto.setState("S");
		
		instructionsDAO.insert(instructsDto);
		
		return "ok";
	}
	
	//http://localhost:8088/instructions/detail/20231219A0001
	@GetMapping("/detail/{code}")
	public String detailGET(@PathVariable("code")String code) {
		
		InstructionsDTO instructionsDTO = instructionsDAO.selectByCode(code);
		
		return instructionsDTO.toString();
	}
	

}
