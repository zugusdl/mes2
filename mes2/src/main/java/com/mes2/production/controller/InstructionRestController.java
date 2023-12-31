package com.mes2.production.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mes2.production.domain.InstructionsDTO;
import com.mes2.production.etc.InstructionsSearchParam;
import com.mes2.production.etc.RequestMaterialDTO;
import com.mes2.production.etc.RequestMaterialInfo;
import com.mes2.production.etc.RequestMaterialsDTO;
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
	//http://localhost:8088/restInstruction/getMaterials
	@PostMapping(value="/getMaterials" , produces ="application/json; charset=utf-8")
	public RequestMaterialsDTO getMaterials(@RequestBody RequestMaterialInfo info) {
		//String sopCode="ACP-bsp002-ORD-20231231-bsp002-1-100";
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+info.getSopCode());
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+info.getSalesQuantity());
		
		RequestMaterialsDTO rqml = instructionsDAO.selectBySopCodeForMaterials(info.getSopCode());
		for(RequestMaterialDTO dto : rqml.getMaterialList()) {
			dto.setTotalAmount(dto.getAmount()*info.getSalesQuantity());
		}
		
		return rqml;
	}
	
	
	
}
