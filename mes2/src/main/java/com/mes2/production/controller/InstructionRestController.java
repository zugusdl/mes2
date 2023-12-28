package com.mes2.production.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.persistence.InstructionsDAO;
import com.mes2.production.service.InstructionsService;

@RestController
@RequestMapping("/restInstruction")
public class InstructionRestController {

	@Inject
	private InstructionsService instructionsService;
	
	@Inject
	private InstructionsDAO instructionsDAO;

	
	@GetMapping("/getInstructions")
	public List<InstructionsDTO> getInstructions(@ModelAttribute("instructionsSearchParam") InstructionsSearchParam isParam) {
		
		List<InstructionsDTO> instructions = instructionsService.findBySearchParam(isParam);
		return instructions;
	}
	
	@GetMapping("/getProduction")
	public String getProductionGET () {
		
		//Date inputDate,int line , String mdpCode
		
		Date test = Date.valueOf(LocalDate.now());
		int line = 1;
		String mdpCode = "PS10001";
		
		return instructionsService.createLotCode(test, line, mdpCode);
		
	}
	
	
	
	
}
